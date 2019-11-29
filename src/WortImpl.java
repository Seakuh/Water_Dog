package ctr;

import java.util.*;

public class WortImpl implements Wort, Wortersetzungsregel {
	private String orgWort;
	private Wort links;
	private Wort rechts;

	public WortImpl() {
	}

	public WortImpl(String pWort) {

		orgWort = pWort;
	}

	public WortImpl(Wort pLinks, Wort pRechts) {
		links = pLinks;
		rechts = pRechts;
	}

	public String toString() {

		return orgWort;
	}

	public Wort linkeSeite() {

		return links;

	}

	public Wort rechteSeite() {

		return rechts;

	}

	public char position(int k) {

		if (!(k >= orgWort.length())) {
			return orgWort.charAt(k);
		} else {
			return 0;
		}
	}

	public int laenge() {

		return orgWort.length();
	}

	public Wort extrahiere(int k, int m) {

		String speicher = "";

		for (int i = k; i <= m; i++) {

			speicher += orgWort.charAt(i);

		}

		Wort w1 = new WortImpl(speicher);

		return w1;
	}

	public Wort kontrahiere(int k, int m) {

		String speicher = "";

		for (int i = 0; i < orgWort.length(); i++) {

			if (!((i >= k) && (i <= k + m - 1))) {

				speicher += orgWort.charAt(i);

			}
		}

		Wort w2 = new WortImpl(speicher);

		return w2;

	}

	public Wort konkateniere(Wort w) {

		String speicher = orgWort + w;

		Wort w3 = new WortImpl(speicher);

		return w3;

	}

	public Wort einschmelzen(Wort w, int m) {

		String speicher = "";

		for (int i = 0; i <= orgWort.length(); i++) {

			if ((i < m)) {

				speicher = speicher + orgWort.charAt(i);

			} else if (i == m) {

				for (int x = 0; x <= w.laenge() - 1; x++) {

					speicher += w.position(x);

				}

			}

			else
				speicher += orgWort.charAt(i - 1);

		}

		Wort w4 = new WortImpl(speicher);

		return w4;

	}

	public Wort tausche(char c1, char c2) {

		String speicher = "";

		for (int i = 0; i < orgWort.length(); i++) {

			if (orgWort.charAt(i) == c1) {

				speicher += c2;
			} else {

				speicher += orgWort.charAt(i);

			}

		}

		Wort w5 = new WortImpl(speicher);

		return w5;
	}

	public int istTeilwortVon(Wort w) {

		int pos = 0;
		int counter = 1;

		if (!orgWort.contains(w.toString())) {

			return 0;
		}

		if (orgWort.length() < w.laenge()) {

			return 0;
		}
		int e = 0;

		for (int i = 0; i < orgWort.length(); i++) {

			if (e != w.laenge()) {
				if (w.position(e) == orgWort.charAt(i)) {
					pos = i;
					e++;

				} else if (w.position(0) == orgWort.charAt(counter)) {
					i = counter;
					pos = i;
					e = 1;
					counter++;
				} else {

					e = 0;
				}

			}

		}

		return pos - (w.laenge() - 1) + 1;

	}

	public int anzahl(char c) {

		int anz = 0;

		for (int i = 0; i <= orgWort.length() - 1; i++) {

			if (orgWort.charAt(i) == c) {

				anz++;
			}
		}

		return anz;

	}

	public Wort ersetze(Wort w1, Wort w2) {

		Wort speicher = new WortImpl(orgWort);

		int sp = speicher.istTeilwortVon(w1);

		if (sp != 0) {
			speicher = speicher.kontrahiere(sp - 1, w1.laenge());
			speicher = speicher.einschmelzen(w2, sp - 1);

		}

		return speicher;

	}

	public boolean ableitbar(Collection<Wortersetzungsregel> regeln, Wort start, Wort ziel) {

		System.out.println("Start: " + start + "    Ziel: " + ziel);

		for (Iterator<Wortersetzungsregel> it = regeln.iterator(); it.hasNext();) {

			WortImpl regel = (WortImpl) it.next();

			if (!(start.toString().equals(ziel.toString()))) {
				while (start.istTeilwortVon(regel.links) != 0) {

					start = start.ersetze(regel.links, regel.rechts);
					System.out.println("Regeln " + regel.links + " --> " + regel.rechts);
					System.out.println(start);
					System.out.println();
				}
			}
			if (start.toString().equals(ziel.toString())) {
				{
					System.out.println(start + " = " + ziel);
					System.out.println("");
					return true;

				}
			}

		}

		return false;
	}

}