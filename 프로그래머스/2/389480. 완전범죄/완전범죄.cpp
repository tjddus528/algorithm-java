#include <string>
#include <vector>
#include <algorithm>
using namespace std;
const int INF = 987654321;

vector<int> dp;

int solution(vector<vector<int>> info, int n, int m) {
    int answer = INF;
    dp.resize(m, INF);
    dp[0] = 0;
    
    for(int i = 0; i < info.size(); i++) {
        vector<int> temp(m, INF);
        
        for(int b_evid = 0; b_evid < m; b_evid++) {
            if (dp[b_evid] == INF)
                continue;
            
            //A가 가져감
            if (dp[b_evid] + info[i][0] < n) {
                temp[b_evid] = min(temp[b_evid], dp[b_evid] + info[i][0]);
            }
            //B가 가져감
            if (b_evid + info[i][1] < m) {
                temp[b_evid + info[i][1]] = min(temp[b_evid + info[i][1]], dp[b_evid]);
            }
        }
        dp = temp;
    }
    
    for(int i = 0; i < m; i++) {
        answer = min(answer, dp[i]);
    }    
    
    return (answer == INF) ? -1 : answer;
}