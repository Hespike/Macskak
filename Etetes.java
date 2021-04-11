public class Etetes {
    static boolean megetetve(Macska[] macskak, int darabszam) {
        for (int i = 0; i < darabszam; i++) {
            if (macskak[i].etelIgeny != 0) {
                return false;
            }
        }
        return true;
    }

    static void megetet(Macska[] macskak, int[] etetendoMacskak, int etetendoMacskakDarabszam) {
        if (etetendoMacskak == null) {
            return;
        }

        for (int i = 0; i < etetendoMacskakDarabszam; i++) {
            macskak[etetendoMacskak[i]].etelIgeny = 0;
        }
    }

    static int CNK(int n, int k) {
        float eredmeny = 1;
        for (int i = 1; i <= k; i++) {
            eredmeny *= ((n + 1 - i) / (double) i);
        }

        return (int) Math.ceil(eredmeny);
    }

    static boolean tombKorrigalas(int[] tomb, int meret, int max) {
        for (int i = meret - 1; i >=0; i--) {
            if (tomb[i] < max + (i-meret)){
                tomb[i]++;

                for (int j = i+1; j < meret; j++) {
                    tomb[j] = tomb[j - 1] + 1;
                }
                return true;
                }
            }
        return false;
        }

        static int[][] opcioLegeneralas(int darabszam, int kivalasztottSzam) {
        if (kivalasztottSzam > darabszam) {
            return null;
        }

        int db = CNK(darabszam, kivalasztottSzam);
        int[][] tomb = new int[db + 10][];

        for (int i = 0; i < db + 10; i++) {
            tomb[i] = null;
        }

        int pos = 1;
        int[] start = new int[kivalasztottSzam];
        for (int i = 0; i < kivalasztottSzam; i++){
            start[i] = i;
        }

        tomb[0] = start;
        while (true) {
            int[] kistomb = new int [kivalasztottSzam];

            for (int i = 0; i < kivalasztottSzam; i++) {
                kistomb[i] = tomb[pos-1][i];
            }

            kistomb[kivalasztottSzam - 1]++;

            for (int i = 0; i < kivalasztottSzam; i++) {
                if (kistomb[i] >= darabszam) {
                    boolean ret = tombKorrigalas(kistomb, kivalasztottSzam, darabszam);
                if (!ret) {
                    return tomb;
                }
                }
            }

            tomb[pos] = kistomb;
            pos++;
        }
    }
static int[] opciokatKivalaszt(Macska[] macskak, int[][] opciok, int kivalasztottSzam, int szuksegesOsszeg, String szin){
for (int i = 0; opciok[i] != null; i++) {
    int osszeg = 0;
    for (int j= 0; j < kivalasztottSzam;j++) {
        if (!macskak[opciok[i][j]].szin.equals(szin)) {
            osszeg = 0;
            break;
        }

        osszeg += macskak[opciok[i][j]].etelIgeny;
    }

    if (osszeg == szuksegesOsszeg){
    return opciok[i];
    }
}
return null;
}

static int[] vizsgalat(Macska[] macskak, int darabszam, int etetendoMacskakSzama, String macskaSzin, int osszEtel){
        int[][] opciok = opcioLegeneralas(darabszam, etetendoMacskakSzama);
        int[] etetesAdat= opciokatKivalaszt(macskak, opciok, etetendoMacskakSzama, osszEtel, macskaSzin);
        return etetesAdat;
}
static int etetes(Macska[] macskak, int darabszam) {
        int etetesAlkalom = 0;

        int egyszerreEtetett = 1;
        int etetesMennyiseg = 10;
        while (!megetetve(macskak, darabszam)) {
            for (int i = 0; i < darabszam; i++){
                if (macskak[i].etelIgeny == 0){
                    continue;
                }

                int[] eredmeny = vizsgalat(macskak, darabszam, egyszerreEtetett, macskak[i].szin, etetesMennyiseg);

                if (eredmeny!= null) {
                    megetet(macskak, eredmeny, egyszerreEtetett);
                    etetesAlkalom++;
                }
            }

            egyszerreEtetett++;
            if (egyszerreEtetett > 10 || egyszerreEtetett > darabszam) {
                egyszerreEtetett = 1;
                etetesMennyiseg--;

            }
        }
        return etetesAlkalom;
}
}
