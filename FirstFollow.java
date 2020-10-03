import java.io.*;
import java.util.*;

class FirstFollow{

	static String var[];
	static String prod[];
	static Scanner scr;
	static int n;
	static String v[];
	static int imap[][];
	static int cnt=0;
	static boolean progress;
	static char follow[][];
	static int follow_cnt[];
	
	public static void main(String...args){
		
		scr=new Scanner(System.in);
		int i=0,j=0;
		var=new String[10];
		prod=new String[10];
		v=new String[10];
		imap=new int[10][10];
		follow=new char[10][10];
		follow_cnt=new int[10];
		
		
		System.out.print("Enter no. of production: ");
		n=scr.nextInt();
		System.out.println("Enter grammar: ");
		for(i=0;i<n;i++){
			var[i]=scr.next();
			prod[i]=scr.next();
			j=0;
			for(j=0;j<cnt;j++){
				if(v[j].equals(var[i])){
					break;
				}
			}
			imap[j][imap[j][0]+1]=i;
			v[j]=var[i];
			imap[j][0]++;
			if(j==cnt){
				cnt++;
			}	
		}

		System.out.println("\n\nFIRST SET: ");
		for(i=0;i<cnt;i++){
			System.out.print(v[i]+" : ");
			firstset(v[i]);
			System.out.println();
		}
		
		for(j=0;j<5;j++){
			for(i=0;i<cnt;i++){			
				followset(v[i],v[i]);
			}
		}
		System.out.println("\n\nFOLLOW SET: ");		
		for(j=0;j<cnt;j++){
			System.out.print(v[j]+" : ");
			for(i=0;i<follow_cnt[j];i++){
				System.out.print(follow[j][i]+" ");							
			}
			System.out.println();
		}
			
	}
	
	static void firstset(String va){
		int i=0,j=0;
			
		for(i=0;i<cnt;i++){
			if(va.equals(v[i])){
				break;
			}
		}
		
		for(j=1;j<=imap[i][0];j++){
			
				if(Character.isLowerCase(prod[imap[i][j]].charAt(0))){
					System.out.print(prod[imap[i][j]].charAt(0)+" ");
					break;
				}
				else{
					firstset(String.valueOf(prod[imap[i][j]].charAt(0)));
				}

		}		
	
	}
	
	static void firstset2(String va,int for_int){
		int i=0,j=0;	
		
		if(Character.isLowerCase(va.charAt(0))){
			for(i=0;i<follow_cnt[for_int];i++){
				if(va.charAt(0)==follow[for_int][i]){
					break;
				}
			}
			if(i==follow_cnt[for_int]){
				follow[for_int][follow_cnt[for_int]]=va.charAt(0);
				follow_cnt[for_int]++;
			}
			progress=false;
		}
		
		for(i=0;i<cnt;i++){
			if(va.equals(v[i])){
				break;
			}
		}
		
		for(j=1;j<=imap[i][0];j++){
			
				if(Character.isLowerCase(prod[imap[i][j]].charAt(0))){
					if(prod[imap[i][j]].charAt(0)=='@'){
						progress=true;
						break;
					}
					else{					
						for(i=0;i<follow_cnt[for_int];i++){
							if(va.charAt(0)==follow[for_int][i]){
								break;
							}
						}
						if(i==follow_cnt[for_int]){
							follow[for_int][follow_cnt[for_int]]=va.charAt(0);
							follow_cnt[for_int]++;
						}
						progress=false;
						break;
					}	
				}
				else{
					firstset(String.valueOf(prod[imap[i][j]].charAt(0)));
				}

		}		
	
	}
	
	static void followset(String va,String for_var){
		int i,j,k,for_int=0;
		
		for(i=0;i<cnt;i++){
			if(v[i].equals(for_var)){
				for_int=i;
			}
		}
		
		for(i=0;i<n;i++){
			upper: for(j=0;j<prod[i].length();j++){
				if(String.valueOf(prod[i].charAt(j)).equals(va)){
					for(k=j+1;k<=prod[i].length();k++){
						if(k==prod[i].length()){
							followset(var[i],va);
						}
						else{
							progress=false;
							firstset2(String.valueOf(prod[i].charAt(k)),for_int);
							if(!progress){
								break upper;
							}
						}
					}
				}
			}
		}
	}
}
