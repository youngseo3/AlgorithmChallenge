#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

std::vector<int> search(int start, int N, const std::vector<std::vector<int>>& graph) {
    std::vector<bool> visited(N + 1, false);
    std::queue<int> q;
    std::vector<int> reachable_nodes;

    q.push(start);
    visited[start] = true;
    reachable_nodes.push_back(start);

    while (!q.empty()) {
        int curr = q.front();
        q.pop();

        for (int next : graph[curr]) {
            if (!visited[next]) {
                visited[next] = true;
                q.push(next);
                reachable_nodes.push_back(next);
            }
        }
    }
    return reachable_nodes;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int N, M;
    std::cin >> N >> M;

    std::vector<std::vector<int>> graph(N + 1);
    for (int i = 0; i < M; ++i) {
        int A, B;
        std::cin >> A >> B;
        graph[B].push_back(A);
    }

    int max_count = 0;
    std::vector<int> counts(N + 1, 0);

    for (int i = 1; i <= N; ++i) {
        counts[i] = search(i, N, graph).size();
        max_count = std::max(max_count, counts[i]);
    }

    for (int i = 1; i <= N; ++i) {
        if (counts[i] == max_count) {
            std::cout << i << " ";
        }
    }
    std::cout << "\n";

    return 0;
}