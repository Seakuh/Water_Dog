package ctr;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class WortTest {
	@Test
	void testExtrahiere() {
		Wort w1 = new WortImpl("Guten Morgen");
		Wort w2 = w1.extrahiere(7, 6);
		assertEquals(new WortImpl("Morgen"), w2);
		Wort w3 = w2.extrahiere(2, 2);
		assertEquals(new WortImpl("or"), w3);
		assertEquals(new WortImpl(""), w1.extrahiere(7, 6).extrahiere(2, 0));
	}

	public static void main(String arg[]) {
		WortImpl s = new WortImpl("Wir fahren an die Illerbruecke");
		WortImpl k = new WortImpl("haus");
		WortImpl m = new WortImpl("baum");

		System.out.println("Postition:");
		System.out.println(k.position(3));
		System.out.println();
		System.out.println("Laenge:");
		System.out.println(s.laenge());
		System.out.println();
		System.out.println("Extrahiere:");
		System.out.println(s.extrahiere(2, 3));
		System.out.println();
		System.out.println("Kontrahiere:");
		System.out.println(s.kontrahiere(1, 2));
		System.out.println();
		System.out.println("Konkateniere:");
		System.out.println(s.konkateniere(k));
		System.out.println();
		System.out.println("Einschmelzen:");
		System.out.println(s.einschmelzen(m, 4));
		System.out.println();
		System.out.println("Tausche:");
		System.out.println(s.tausche('n', 'z'));
		System.out.println();
		System.out.println("Teilwort:");
		System.out.println(s.istTeilwortVon(k));
		System.out.println();
		System.out.println("Anzahl:");
		System.out.println(s.anzahl('a'));
		System.out.println();
		System.out.println("Ersetze:");
		System.out.println(s.ersetze(k, m));

		WortImpl test = new WortImpl();
		WortImpl regel1 = new WortImpl(new WortImpl("00"), new WortImpl("0"));
		WortImpl regel2 = new WortImpl(new WortImpl("11"), new WortImpl("1"));

		Wort start = new WortImpl("01111000100011100000000000");
		Wort ziel = new WortImpl("0101010");

		LinkedList<Wortersetzungsregel> regeln = new LinkedList<Wortersetzungsregel>();
		regeln.add(regel1);
		regeln.add(regel2);
		System.out.println();
		System.out.println(test.ableitbar(regeln, start, ziel));
		System.out.println("--- Aufgabe 5 ---");

		Wort start1 = new WortImpl("ababcab");
		Wort ziel1 = new WortImpl("aaabbbc");

		WortImpl test1 = new WortImpl();
		WortImpl regel41 = new WortImpl(new WortImpl("ba"), new WortImpl("ab"));
		WortImpl regel51 = new WortImpl(new WortImpl("bab"), new WortImpl("abb"));
		WortImpl regel31 = new WortImpl(new WortImpl("ca"), new WortImpl("ac"));
		WortImpl regel11 = new WortImpl(new WortImpl("bca"), new WortImpl("abc"));
		WortImpl regel21 = new WortImpl(new WortImpl("cba"), new WortImpl("abc"));
		WortImpl regel61 = new WortImpl(new WortImpl("cb"), new WortImpl("bc"));
		WortImpl regel71 = new WortImpl(new WortImpl("cbc"), new WortImpl("bcc"));

		LinkedList<Wortersetzungsregel> regeln1 = new LinkedList<Wortersetzungsregel>();

		regeln1.add(regel11);
		regeln1.add(regel21);
		regeln1.add(regel31);
		regeln1.add(regel41);
		regeln1.add(regel51);
		regeln1.add(regel61);
		regeln1.add(regel71);

		System.out.println(test1.ableitbar(regeln1, start1, ziel1));
		
	}
}