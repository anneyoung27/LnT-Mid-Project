package program;

import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import jabatan.Admin;
import jabatan.Karyawan;
import jabatan.Manager;
import jabatan.Supervisor;

public class Main {
	Scanner in = new Scanner(System.in);
	Vector<Karyawan> vKaryawan = new Vector<>();
	String namaKaryawan, jenisKelamin, jabatan;
	public Main() {
		int choose = 0;
		do {
			menu();
			choose = in.nextInt(); in.nextLine();
			
			switch(choose) {
				case 1: 
					cls();
					inputDataKaryawan();
					break;
				case 2:
					cls();
					tampilkanDataKaryawan();
					break;
				case 3:
					cls();
					updateDataKaryawan();
					break;
				case 4:
					cls();
					hapusDataKaryawan();
					break;
				case 5:
					return;
					
			}
		}while(choose != 5);
	}
	
	void cls(){
        for(int i = 0; i < 40; i++){
            System.out.println();
        }
    }
	
	void menu() {
		System.out.println("PT. Musang");
		System.out.println("1. Input data karyawan");
		System.out.println("2. Tampilkan data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Hapus data karyawan");
		System.out.println("5. Keluar");
		System.out.print(">> ");
	}
	
	void inputDataKaryawan() {
		Karyawan k;
		
		do {
			System.out.print("Input nama karyawan [>=3]: \n>>");
			namaKaryawan = in.nextLine();
		}while(namaKaryawan.length() <= 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): \n>>");
			jenisKelamin = in.nextLine();
		}while(!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): \n>>");
			jabatan = in.nextLine();
		}while(!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if(jabatan.equals("Manager")) {
			vKaryawan.add(new Manager(generateID(), namaKaryawan, jenisKelamin, jabatan, 8_000_000));
		}else if(jabatan.equals("Supervisor")) {
			vKaryawan.add(new Supervisor(generateID(), namaKaryawan, jenisKelamin, jabatan, 6_000_000));
		}else {
			vKaryawan.add(new Admin(generateID(), namaKaryawan, jenisKelamin, jabatan, 4_000_000));
		}
		
		System.out.println("Karyawan berhasil ditambahkan!");
		System.out.println("ENTER to return");
		in.nextLine();
	}
	
	private String generateID(){
        Random rdm = new Random();
        int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 2;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
		   int randomLimitedInt = leftLimit + (int) 
		   (rdm.nextFloat() * (rightLimit - leftLimit + 1));
		   buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString().toUpperCase();
        int randomNumber = rdm.nextInt(1000);
        generatedString += String.format("%03d", randomNumber);

        return generatedString;
    }
	
	void tampilkanDataKaryawan() {
		int idx = 0;
		if(vKaryawan.isEmpty()) {
			System.out.println("Tidak ada karyawan yang terdaftar..");
			System.out.println("ENTER to return");
			in.nextLine();
		}else {
			sortAscendingByName();
			for(Karyawan k : vKaryawan) {
				 System.out.println("=============================");
				 System.out.println("ID "+ ++idx+":");
				 System.out.println("Kode Karyawan :"+k.getKodeKaryawan());
				 System.out.println("Nama Karyawan :"+k.getNama());
				 System.out.println("Jenis Kelamin :"+k.getJenisKelamin());
				 System.out.println("Jabatan :"+k.getJabatan());
				 
				 if(k instanceof Admin) {
					 System.out.println("Gaji Karyawan :"+(k.hitungGaji(5)) + ((Admin)k).getGaji());
					 System.out.println("=============================");
				 }else if(k instanceof Manager) {
					 System.out.println("Gaji Karyawan :"+k.hitungGaji(10) + ((Manager)k).getGaji());
					 System.out.println("=============================");
				 }else {
					 System.out.println("Gaji Karyawan :"+k.hitungGaji(7.5) + ((Supervisor)k).getGaji());
					 System.out.println("=============================");
				 }
			}
		}
	}
	
	// sorting
	void sortAscendingByName() {
		 vKaryawan.sort(new Comparator<>() {
			 public int compare(Karyawan a, Karyawan b) {
				 return b.getNama().compareTo(a.getNama());
			 }
		});
	}
	
    void updateDataKaryawan(){
    	String nama = null, kelamin = null, jabatanBaru = null;
        if(vKaryawan.isEmpty()){
            System.out.println("Tidak ada data yang perlu diupdate!");
            System.out.println("ENTER to conitinue.");
            in.nextLine();
        }else{
            int index =0;
            tampilkanDataKaryawan();
            do{
                // check if numeric
                try{
                    System.out.print("Input nomor urutan karyawan yang ingin diupdate [1 - "+vKaryawan.size()+"]: ");
                    index = in.nextInt();
                }catch (Exception e){
                    index = -1;
                    System.out.println("[!] Input harus dalam numerik!");
                }
                in.nextLine();
            }while(index < 1 || index > vKaryawan.size());

            do{
                System.out.print("Input nama karyawan [>= 3]: ");
                nama = in.nextLine();
            }while(nama.length() >= 3);

            do{
                try{
                    System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
                    kelamin = in.nextLine();
                }catch (Exception e){
                    if(kelamin.equals("0") || kelamin.equals("1")) return;
                }
            }while(!kelamin.equals("Laki-laki") && !kelamin.equals("Perempuan"));
            
            do {
            	System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
            	jabatanBaru = in.nextLine();
            }while(!jabatanBaru.equals("Manager") && !jabatanBaru.equals("Supervisor") && !jabatanBaru.equals("Admin"));
            
            if(vKaryawan.get(index - 1) instanceof Admin ){
            	vKaryawan.set(index - 1, new Admin("",nama,kelamin,jabatan,0));
                System.out.println("Student updated!");
            }else if(vKaryawan.get(index - 1) instanceof Manager) {
            	vKaryawan.set(index - 1, new Manager("", nama, kelamin, jabatan, 0));
            }else if(vKaryawan.get(index - 1) instanceof Supervisor) {
            	vKaryawan.set(index - 1, new Supervisor("", nama, kelamin, jabatan, 0));
            }
            System.out.println("Berhasil mengupdate karyawan "+ vKaryawan.get(index));
            System.out.println("ENTER to return.");
            in.nextLine();
        }
            
    }

	
    void hapusDataKaryawan(){
        if(vKaryawan.isEmpty()){
            System.out.println("Tidak ada data!");
            System.out.println("Enter to continue..");
            in.nextLine();
        }else{
            int idx = 0;
            tampilkanDataKaryawan();
            do{
                try{
                    System.out.print("Input nomor urutan karyawan yang ingin dihapus: [1 - "+vKaryawan.size()+"]: ");
                    idx = in.nextInt();
                }catch (Exception e){
                    idx = -1;
                    System.out.println("[!] Harus numeric! ");
                }
            }while(idx < 1 || idx > vKaryawan.size());
            vKaryawan.remove(idx - 1);
            System.out.println("Delete success!");
        }
    }
    
	public static void main(String[] args) {
		new Main();
	}
}
