// Last updated: 9/21/2025, 1:30:46 AM
class Router {
    private final int memoryLimit;
    private final Queue<int[]> packetsQueue;
    private final Set<String> uniquePackets;
    private final Map<Integer, List<Integer>> destinationTimestamps;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.packetsQueue = new LinkedList<>();
        this.uniquePackets = new HashSet<>();
        this.destinationTimestamps = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String packetKey = source + "-" + destination + "-" + timestamp;
        if (uniquePackets.contains(packetKey)) {
            return false;
        }

        if (packetsQueue.size() == memoryLimit) {
            int[] oldestPacket = packetsQueue.poll();
            int oldSource = oldestPacket[0];
            int oldDestination = oldestPacket[1];
            int oldTimestamp = oldestPacket[2];

            String oldestPacketKey = oldSource + "-" + oldDestination + "-" + oldTimestamp;
            uniquePackets.remove(oldestPacketKey);

            List<Integer> timestamps = destinationTimestamps.get(oldDestination);
            if (timestamps != null) {
                timestamps.remove(0);
                if (timestamps.isEmpty()) {
                    destinationTimestamps.remove(oldDestination);
                }
            }
        }

        int[] newPacket = {source, destination, timestamp};
        packetsQueue.add(newPacket);
        uniquePackets.add(packetKey);

        destinationTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        return true;
    }

    public int[] forwardPacket() {
        if (packetsQueue.isEmpty()) {
            return new int[0];
        }

        int[] forwardedPacket = packetsQueue.poll();
        int source = forwardedPacket[0];
        int destination = forwardedPacket[1];
        int timestamp = forwardedPacket[2];

        String packetKey = source + "-" + destination + "-" + timestamp;
        uniquePackets.remove(packetKey);

        List<Integer> timestamps = destinationTimestamps.get(destination);
        if (timestamps != null) {
            timestamps.remove(0);
            if (timestamps.isEmpty()) {
                destinationTimestamps.remove(destination);
            }
        }

        return forwardedPacket;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destinationTimestamps.containsKey(destination)) {
            return 0;
        }

        List<Integer> timestamps = destinationTimestamps.get(destination);

        int startIndex = lower_bound(timestamps, startTime);
        int endIndex = upper_bound(timestamps, endTime);

        return endIndex - startIndex;
    }

    private int lower_bound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int upper_bound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}