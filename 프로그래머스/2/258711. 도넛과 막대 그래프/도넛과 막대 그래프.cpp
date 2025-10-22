#include <string>
#include <vector>
#include <queue>
#include <unordered_map>
#include <iostream>
#define MAX_N 1000001
using namespace std;
vector<int> visited;
unordered_map<int, int> indegree;
unordered_map<int, int> outdegree;
unordered_map<int, vector<int>> groups;

void bfs(int start, int number, vector<vector<int>>& use_edges) {
    queue<int> q;

    visited[start] = number;
    q.push(start);

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int next : use_edges[here]) {
            if (visited[next] == -1) {
                groups[number].push_back(next);
                visited[next] = number;
                q.push(next);
            }
        }
    }
}

int check_type(vector<int> numbers) {
    for (int next : numbers) {
        if (indegree[next] == 2 && outdegree[next] == 2)
            return 2;
        else if (indegree[next] == 0 || outdegree[next] == 0)
            return 1;
    }
    return 0;
}

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;

    visited = vector<int>(MAX_N, -1);
    vector<vector<int>> new_edges = vector<vector<int>>(MAX_N);
    vector<vector<int>> verse_edges = vector<vector<int>>(MAX_N);

    for (int i = 0; i < edges.size(); i++) {
        int a = edges[i][0];
        int b = edges[i][1];

        if (indegree.count(a) == 0) {
            indegree[a] = 0;
            outdegree[a] = 0;
        }
        if (indegree.count(b) == 0) {
            indegree[b] = 0;
            outdegree[b] = 0;
        }

        outdegree[a] += 1;
        indegree[b] += 1;

        new_edges[a].push_back(b);
        verse_edges[b].push_back(a);
    }

    for (int i = 1; i < MAX_N; i++) {
        if (!(indegree[i] == 0 && outdegree[i] >= 2))
            continue;

        answer.push_back(i);
        visited[i] = 0;

        int group_number = 1;
        //번호 부여
        for (int next : new_edges[i]) {
            indegree[next] -= 1;
            groups[group_number] = {};
            groups[group_number].push_back(next);
            bfs(next, group_number, new_edges);
            bfs(next, group_number, verse_edges);
            group_number++;
        }

        vector<int> count(3, 0);
        //각 그룹에 대해서 종류 확인
        for (int number = 1; number < group_number; number++) {
            count[check_type(groups[number])]++;
        }

        for (int i = 0; i < 3; i++) {
            answer.push_back(count[i]);
        }
        break;
    }

    return answer;
}