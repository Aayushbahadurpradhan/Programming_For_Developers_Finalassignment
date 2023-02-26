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
    // increment the frequency of a cache block
    public void increment(int value)
    {
        if (cache.containsKey(value)) {
            cache.get(value).frequency += 1;
        }
    }

    // insert a new cache block
    public void insert(int value)
    {
        // if the cache is full, remove the least frequently used block
        if (cache.size() == cacheSize) {
            int lfuKey = findLFU();
            System.out.println("Cache block " + lfuKey + " removed.");
            cache.remove(lfuKey);
        }

        // insert the new block with frequency 1
        Pair newPair = new Pair(value, 1);
        cache.put(value, newPair);
        System.out.println("Cache block " + value + " inserted.");
    }

    // refer to a cache block
    public void refer(int value)
    {
        // if the block is not in the cache, insert it
        if (!cache.containsKey(value)) {
            insert(value);
        }
        // otherwise, increment its frequency
        else {
            increment(value);
        }
    }

    // find the least frequently used block in the cache
    public int findLFU()
    {
        int lfuKey = 0;
        int minFrequency = Integer.MAX_VALUE;

        // iterate over all cache blocks to find the least frequently used one
        for (Map.Entry<Integer, Pair> entry : cache.entrySet()) {
            if (entry.getValue().frequency < minFrequency) {
                minFrequency = entry.getValue().frequency;
                lfuKey = entry.getKey();
            }
        }

        return lfuKey;
    }

}


