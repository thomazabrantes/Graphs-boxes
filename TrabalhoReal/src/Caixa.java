import java.util.Arrays;

public class Caixa implements Comparable<Caixa> {
    int[] dimensoes;

    public Caixa(int[] dimensoes) {
        this.dimensoes = dimensoes;
        Arrays.sort(this.dimensoes);
    }

    public boolean cabeDentro(Caixa outraCaixa) {
        Arrays.sort(this.dimensoes);
        Arrays.sort(outraCaixa.dimensoes);
        for (int i = 0; i < dimensoes.length; i++) {
            if (dimensoes[i] >= outraCaixa.dimensoes[i]) {
                return false;
            }
        }
        return true;
    }
    

    @Override
    public int compareTo(Caixa outraCaixa) {
        for (int i = 0; i < this.dimensoes.length; i++) {
            if (this.dimensoes[i] != outraCaixa.dimensoes[i]) {
                return this.dimensoes[i] - outraCaixa.dimensoes[i];
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(dimensoes);
    }
}
