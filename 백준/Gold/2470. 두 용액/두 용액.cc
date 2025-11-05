#include <bits/stdc++.h>
#include <ios>
#include <limits>
#include <vector>

using namespace std;
using ll = long long;
ll INF = numeric_limits<ll>::max();
// -99 -2 -1 1 4 98


ll n;
vector<ll> v;

void init(){
	cin >> n;
	v.assign(n, 0);
	for (int i = 0; i < n; ++i)
	{
		cin >> v[i];
	}
	sort(v.begin(), v.end());
}

vector<ll> solve(){
	vector<ll> ans;
	ll min_gap = INF;
	int st = 0 , ed = n - 1;
	while (st < ed) {
		ll result = v[st] + v[ed];
		ll gap = abs(result);
		if(gap < min_gap){
			min_gap = gap;
			ans.clear();
			ans.push_back(min(v[st], v[ed]));
			ans.push_back(max(v[st], v[ed]));
		}


		if(result > 0) {
			ed--;
		}
		else if( result < 0){
			st++;
		}
		else{
			return ans;
		}
	}


	return ans;
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	init();
	auto ans = solve();
	cout << ans[0] << " " << ans[1];
}