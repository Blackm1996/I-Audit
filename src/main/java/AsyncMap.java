

import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;

import java.util.Collections;
import java.util.concurrent.Future;

public class AsyncMap implements AsyncFunction<String, Entry> {

    public void asyncInvoke(String row, final ResultFuture<Entry> result) throws Exception {
        /*Get get = new Get(Bytes.toBytes(row));
        ListenableFuture<Result> future = hbase.asyncGet(get);
        Futures.addCallback(future, new FutureCallback<Result>() {
            public void onSuccess(Result result) {
                List<String> ret = process(result);
                result.complete(ret);
            }
            public void onFailure(Throwable thrown) {
                result.completeExceptionally(thrown);
            }
        });*/
        if (row.charAt(0)=='l')
        {
            Access_Entry e=new Access_Entry(row);
            result.complete(Collections.singleton(e));
        }
        else
            result.complete(Collections.singleton(new Action_Entry(row)));
    }
}
