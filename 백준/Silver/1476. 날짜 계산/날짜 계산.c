#include <stdio.h>
int main() {
	int eread, sread, mread, year = 0, e = 1, s = 1 ,m = 1;
	scanf("%d %d %d", &eread, &sread, &mread);
	while (1) {
		year += 1;
		if (eread == e && sread == s && mread == m) {
			printf("%d", year);
			break;
		}
		e += 1;
		s += 1;
		m += 1;
		if (e > 15) e = 1;
		if (s > 28) s = 1;
		if (m > 19) m = 1;
	}
	return 0;
}