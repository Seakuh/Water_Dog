package ctr;

public interface Wort {
	char position(int k);
	int laenge();
	Wort extrahiere(int k, int m);
	Wort kontrahiere(int k, int m);
	Wort konkateniere(Wort w);
	Wort einschmelzen(Wort w, int m);
	Wort tausche(char c1, char c2);
	int istTeilwortVon(Wort w);
	int anzahl(char c);
	Wort ersetze(Wort w1, Wort w2);
}
