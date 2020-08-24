import com.podcrash.gamecore.data.RedisDataSource;
import com.podcrash.gamecore.leaderboard.Leaderboard;
import com.podcrash.gamecore.leaderboard.LeaderboardEntry;
import com.podcrash.gamecore.leaderboard.LeaderboardType;
import org.junit.Test;

import java.util.Set;

public class LeaderboardTest {

    @Test
    public void test() {
        RedisDataSource redisDataSource = new RedisDataSource();
        Leaderboard leaderboard = new Leaderboard("ms", redisDataSource);
        Set<LeaderboardEntry> leaderboardEntries = leaderboard.fetchLeaderboardData(LeaderboardType.ALL);
        System.out.println(leaderboardEntries);
    }
}
