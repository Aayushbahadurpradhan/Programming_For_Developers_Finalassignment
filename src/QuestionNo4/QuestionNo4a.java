package QuestionNo4;

import java.util.HashMap;
import java.util.Map;

public class QuestionNo4a {
    public static void main(String[] args)
    {
        LFU lfuCache = new LFU(4);
        lfuCache.refer(1);
        lfuCache.refer(2);
        lfuCache.refer(1);
        lfuCache.refer(3);
        lfuCache.refer(2);
        lfuCache.refer(4);
        lfuCache.refer(5);
    }
}
class Pair {
    int value, frequency;
    public Pair(int value, int frequency)
    {
        this.value = value;
        this.frequency = frequency;
    }
}
class LFU {
    int cacheSize;
    Map<Integer, Pair> cache;
    public LFU(int cacheSize)
    {
        this.cacheSize = cacheSize;
        this.cache = new HashMap<Integer, Pair>();
    }

    public void increment(int value)
    {
        if (cache.containsKey(value)) {
            cache.get(value).frequency += 1;
        }
    }
    public void insert(int value)
    {
        if (cache.size() == cacheSize) {
            int lfuKey = findLFU();
            System.out.println("Cache block " + lfuKey + " removed.");
            cache.remove(lfuKey);
        }
        Pair newPair = new Pair(value, 1);
        cache.put(value, newPair);
        System.out.println("Cache block " + value + " inserted.");
    }
    public void refer(int value)
    {
        if (!cache.containsKey(value)) {
            insert(value);
        }
        else {
            increment(value);
        }
    }
    public int findLFU()
    {
        int lfuKey = 0;
        int minFrequency = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Pair> entry :
                cache.entrySet()) {
            if (entry.getValue().frequency < minFrequency) {
                minFrequency = entry.getValue().frequency;
                lfuKey = entry.getKey();
            }
        }
        return lfuKey;
    }
}


