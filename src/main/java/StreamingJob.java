/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.functions.TimedOutPartialMatchHandler;
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.crypto.Data;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {
	final static OutputTag<Action_Entry> outputTag = new OutputTag<Action_Entry>("side-output", TypeInformation.of(Action_Entry.class));

	public static void main(String[] args) throws Exception {

		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.getConfig().setAutoWatermarkInterval(1000L);
		String path = Utils.getSettings("Logs_Path");
		TextInputFormat inputFormat = new TextInputFormat(new Path(path));
		inputFormat.setCharsetName("UTF-8");
		DataStream<String> entries =env.readFile(inputFormat,path,FileProcessingMode.PROCESS_ONCE,5000, BasicTypeInfo.STRING_TYPE_INFO)
				.filter(new FilterFunction<String>() {
					@Override
					public boolean filter(String value) throws Exception {
						return value.split("\\s+").length>1;
					}
				})
				.assignTimestampsAndWatermarks(new TSAS())
				.windowAll(TumblingEventTimeWindows.of(Time.seconds(5)))
				.apply(new AllWindowFunction<String, String, TimeWindow>() {
					@Override
					public void apply(TimeWindow window, Iterable<String> values, Collector<String> out) throws Exception {
						for (String e:values)
							out.collect(e);
					}
				});

		DataStream<Entry> data1= AsyncDataStream.orderedWait(entries,new AsyncMap(),120000, TimeUnit.MICROSECONDS,5000);
		DataStream<Access_Entry> access=data1.filter(new FilterFunction<Entry>() {
			@Override
			public boolean filter(Entry value) throws Exception {
				return value instanceof Access_Entry;
			}
		}).map(new MapFunction<Entry, Access_Entry>() {
			@Override
			public Access_Entry map(Entry value) throws Exception {
				return (Access_Entry)value;
			}
		});
		DataStream<Action_Entry> data=data1.filter(new FilterFunction<Entry>() {
			@Override
			public boolean filter(Entry value) throws Exception {
				return value instanceof Action_Entry;
			}
		}).map(new MapFunction<Entry, Action_Entry>() {
			@Override
			public Action_Entry map(Entry value) throws Exception {
				return (Action_Entry)value;
			}
		});

		ArrayList<PatternSpecification> patternsS=Utils.getPatterns();
		ArrayList<DataStream> streams=new ArrayList<>();
        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("127.0.0.1", 9200, "http"));

        for (PatternSpecification p:patternsS)
		{
		    String res=p.Result_name;
		    String name=p.Name;
			DataStream<Action_Entry> d=CEP.pattern(data,p.pattern).process(new PatternProcessFunction<Action_Entry,Action_Entry>() {
				@Override
				public void processMatch(Map<String, List<Action_Entry>> map, Context context, Collector<Action_Entry> collector) throws Exception
                {
                    List<Action_Entry> list=map.get(res);
                    for (Action_Entry a:list)
                        collector.collect(a);
				}
			});
            ElasticsearchSink.Builder<Action_Entry> sink = new ElasticsearchSink.Builder<>(
                    httpHosts,
                    new ElasticsearchSinkFunction<Action_Entry>() {
                        public IndexRequest createIndexRequest(Action_Entry element) {
                            Map<String, String> json = new HashMap<>();
                            json.put("@timestamp", element.getTimeStamp().toString());
                            json.put("actor", element.getActor());
                            json.put("database", element.getDb_name());
                            json.put("type of violation",name);

                            return Requests.indexRequest()
                                    .index("iaudit-violations")
                                    .type("")
                                    .source(json);
                        }

                        @Override
                        public void process(Action_Entry element, RuntimeContext ctx, RequestIndexer indexer) {
							EmailThread t=new EmailThread("email","violation",name);
							t.start();
                            indexer.add(createIndexRequest(element));
                        }
                    }
            );

// configuration for the bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
            sink.setBulkFlushMaxActions(1);
            d.addSink(sink.build());
            streams.add(d);
		}
/*_______________________________________________________________________________________________________________________________________________________*/


// use a ElasticsearchSink.Builder to create an ElasticsearchSink
		ElasticsearchSink.Builder<Access_Entry> elastic_accsess_documents = new ElasticsearchSink.Builder<>(
				httpHosts,
				new ElasticsearchSinkFunction<Access_Entry>() {
					public IndexRequest createIndexRequest(Access_Entry element) {
						Map<String, String> json = new HashMap<>();
						json.put("@timestamp", (element).getTimeStamp().toString());
						json.put("actor", (element).getActor());
						json.put("database", (element).getDb_name());
						json.put("IP", (element).getIP());
						json.put("geo", (element).getCoordinates());

						return Requests.indexRequest()
								.index("iaudit-access")
								.type("")
								.source(json);
					}

					@Override
					public void process(Access_Entry element, RuntimeContext ctx, RequestIndexer indexer) {
						indexer.add(createIndexRequest(element));
					}
				}
		);

		// configuration for the bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
		elastic_accsess_documents.setBulkFlushMaxActions(1);
		access.addSink(elastic_accsess_documents.build());

		// execute program
		env.execute("Flink Streaming Java API Skeleton");

	}

	static class MyPatternProcessFunction<IN, OUT> extends PatternProcessFunction<IN, OUT> implements TimedOutPartialMatchHandler<IN> , ResultTypeQueryable {
		@Override
		public void processMatch(Map<String, List<IN>> match, Context ctx, Collector<OUT> out) throws Exception
		{

		}

	@Override
	public void processTimedOutMatch(Map<String, List<IN>> match, Context ctx) throws Exception {
		List<IN> el = match.get("first insert");
		for (IN i:el)
		{
			Action_Entry e=(Action_Entry) i;
			ctx.output(outputTag,e);
			/*EmailThread t=new EmailThread("email","violation","no_insert_in_5");
			t.start()*/;
		}
	}

		@Override
		public TypeInformation getProducedType() {
			return TypeInformation.of(Action_Entry.class);
		}
	}
}
