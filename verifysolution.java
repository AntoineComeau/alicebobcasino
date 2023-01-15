package alice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class verifysolution {
	static boolean[][] txtstrat= new boolean[2100000][70];
	static int nb = (int)Math.pow(2, 21);
	//static int nb = 200;
	
	public static void main(String args[]) throws IOException
	{
		int i,j,k,tmp,sum;
		long[] alice = new long[145534486];
		boolean[] alicechoice = new boolean[145534486];
		int alicenew=0;
		
		long[] tmpalice = new long[30];
		long ltmp,lsum;
		boolean[] tmpalicechoice = new boolean[30];
		boolean[] tmpalicedone = new boolean[30];
		
		readfile();
		System.out.println("File read: OK");
		
		for(i=0;i<nb;i++)
		{
			System.out.println(i);
			
			//correct casino sequence?
			tmp=1;
			sum=0;
			for(j=3*23-1;j>=2*23+2;j--)
			{
				if(txtstrat[i][j]) sum+=tmp;
				tmp*=2;
			}
			if(i!=sum) {
				System.out.println("FAILED1:"+i);
				System.exit(0);
			}
			
			//no more than six mistakes?
			tmp=0;
			for(j=0;j<23;j++)
			{
				if(!(txtstrat[i][j]==txtstrat[i][j+23] && txtstrat[i][j]==txtstrat[i][j+46])) tmp++;
			}
			if(tmp>6) {
				System.out.println("FAILED2:"+i);
				System.exit(0);
			}
			
			//consistent Alice strat?
			ltmp=1;
			lsum=0;
			for(j=0;j<22;j++)
			{
				tmpalicedone[j]=false;
				if(txtstrat[i][23+j]) lsum+=ltmp;
				ltmp*=2;
				if(txtstrat[i][46+j]) lsum+=ltmp;
				ltmp*=2;
				
				tmpalice[j]=lsum+ltmp;
				tmpalicechoice[j]=txtstrat[i][j+1];
			}
			
			for(j=0;j<alicenew;j++)
			{
				for(k=0;k<22;k++)
				{
					if(alice[j]==tmpalice[k])
					{
						if(tmpalicechoice[k]!=alicechoice[j])
						{
							System.out.println("FAILED3:"+i);
							System.exit(0);
						}
						else
						{
							tmpalicedone[k]=true;
						}
					}
				}
			}
			
			for(j=0;j<22;j++)
			{
				if(!tmpalicedone[j])
				{
					alice[alicenew]=tmpalice[j];
					alicechoice[alicenew]=tmpalicechoice[j];
					alicenew++;
				}
			}
			
		}
		
		System.out.println("SUCCESS");
	}
	
	public static void readfile() throws IOException
	{
		InputStream os = new FileInputStream("C:\\test\\solv2.txt");
		int vec;
		int i,j;
		
		for(i=0;i<nb;i++)
		{
			for(j=0;j<23;j++)
			{
				vec=os.read();
				if(vec==49) txtstrat[i][j]=true;		
			}
			os.read();
			os.read();
			
			for(j=23;j<2*23;j++)
			{
				vec=os.read();
				if(vec==49) txtstrat[i][j]=true;		
			}
			os.read();
			os.read();
			
			for(j=2*23;j<3*23;j++)
			{
				vec=os.read();
				if(vec==49) txtstrat[i][j]=true;		
			}
			os.read();
			os.read();
			os.read();
			os.read();
		}
		os.close();

	}
	
}
