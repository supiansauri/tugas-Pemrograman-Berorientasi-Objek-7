import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class KeluargaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Keluarga keluarga = new Keluarga();

        boolean lanjutMenu = true;
        while (lanjutMenu) {
            bersihkanLayar();
            System.out.println("Menu:");
            System.out.println("1. Tambah anggota keluarga");
            System.out.println("2. Tambah anak untuk anggota keluarga");
            System.out.println("3. Lihat anggota keluarga");
            System.out.println("q. Keluar");

            System.out.print("Pilihan Anda: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan nama anggota keluarga: ");
                    String nama = scanner.nextLine();
                    AnggotaKeluarga anggota = new AnggotaKeluarga(nama);
                    keluarga.tambahAnggota(anggota);
                    System.out.println("Anggota keluarga berhasil ditambahkan.");
                    break;
                case "2":
                    if (keluarga.getJumlahAnggota() == 0) {
                        System.out.println("Belum ada anggota keluarga. Tambahkan anggota keluarga terlebih dahulu.");
                        break;
                    }
                    System.out.println("Daftar anggota keluarga:");
                    tampilkanAnggotaKeluarga(keluarga);
                    System.out.print("Pilih nomor anggota keluarga: ");
                    int indexAnggota = Integer.parseInt(scanner.nextLine());
                    if (indexAnggota < 0 || indexAnggota >= keluarga.getJumlahAnggota()) {
                        System.out.println("Nomor anggota keluarga tidak valid.");
                        break;
                    }
                    AnggotaKeluarga anggotaDipilih = keluarga.getAnggota(indexAnggota);
                    System.out.print("Masukkan nama anak: ");
                    String namaAnak = scanner.nextLine();
                    AnggotaKeluarga anak = new AnggotaKeluarga(namaAnak);
                    anggotaDipilih.tambahAnak(anak);
                    System.out.println("Anak berhasil ditambahkan untuk " + anggotaDipilih.getNama() + ".");
                    break;
                case "3":
                    if (keluarga.getJumlahAnggota() == 0) {
                        System.out.println("Belum ada anggota keluarga.");
                        break;
                    }
                    System.out.println("Daftar anggota keluarga:");
                    tampilkanAnggotaKeluarga(keluarga);
                    break;
                case "q":
                    lanjutMenu = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            if (lanjutMenu) {
                System.out.print("Tekan enter untuk melanjutkan...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    static void tampilkanAnggotaKeluarga(Keluarga keluarga) {
        for (int i = 0; i < keluarga.getJumlahAnggota(); i++) {
            System.out.println((i + 1) + ". " + keluarga.getAnggota(i).getNama());
            tampilkanAnak(keluarga.getAnggota(i));
        }
    }

    static void tampilkanAnak(AnggotaKeluarga anggota) {
        if (!anggota.getAnak().isEmpty()) {
            System.out.println("   Anak:");
            int index = 1;
            for (AnggotaKeluarga anak : anggota.getAnak()) {
                System.out.println("      " + index + ". " + anak.getNama());
                index++;
            }
        }
    }

    static void bersihkanLayar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class AnggotaKeluarga {
    private String nama;
    private ArrayDeque<AnggotaKeluarga> anak;

    public AnggotaKeluarga(String nama) {
        this.nama = nama;
        anak = new ArrayDeque<>();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void tambahAnak(AnggotaKeluarga anak) {
        this.anak.add(anak);
    }

    public AnggotaKeluarga getAnakPertama() {
        return anak.getFirst();
    }

    public AnggotaKeluarga getAnakTerakhir() {
        return anak.getLast();
    }

    public ArrayDeque<AnggotaKeluarga> getAnak() {
        return anak;
    }
}

class Keluarga {
    private ArrayList<AnggotaKeluarga> anggota;

    public Keluarga() {
        anggota = new ArrayList<>();
    }

    public void tambahAnggota(AnggotaKeluarga anggota) {
        this.anggota.add(anggota);
    }

    public AnggotaKeluarga getAnggota(int indeks) {
        return anggota.get(indeks);
    }

    public int getJumlahAnggota() {
        return anggota.size();
    }
}
