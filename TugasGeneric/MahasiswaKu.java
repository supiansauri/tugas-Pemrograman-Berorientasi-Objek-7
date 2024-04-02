class Mahasiswa<T, U, V> {
    private T nim;
    private U name;
    private V clas;

    public T getNim() {
        return nim;
    }

    public void setNim(T nim) {
        this.nim = nim;
    }

    public U getName() {
        return name;
    }

    public void setName(U name) {
        this.name = name;
    }

    public V getClas() {
        return clas;
    }

    public void setClas(V clas) {
        this.clas = clas;
    }
}

public class MahasiswaKu {
    public static void main(String[] args) {
        Mahasiswa<String, String, Integer> m = new Mahasiswa<>();
        m.setNim("1102020");
        m.setName("Ferdi");
        m.setClas(21);

        System.out.println(m.getNim());
        System.out.println(m.getName());
        System.out.println(m.getClas());
    }
}
