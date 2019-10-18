import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TSAS implements AssignerWithPeriodicWatermarks<String> {
    private final long maxOutOfOrderness = 3500;

    private long currentMaxTimestamp;

    @Override
    public long extractTimestamp(String element, long previousElementTimestamp) {
        String[] parts=element.split("\\s+");
        long timestamp;
        String s;
        if (element.charAt(0)=='l')
        {
            s=parts[1]+"T"+parts[2];
        }
        else
        {
            s=parts[0]+"T"+parts[1];
        }
        timestamp = LocalDateTime.parse(s).toInstant(ZoneOffset.ofHours(2)).toEpochMilli();
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        return timestamp;
    }

    @Override
    public Watermark getCurrentWatermark() {
        // return the watermark as current highest timestamp minus the out-of-orderness bound
        return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
    }
}
