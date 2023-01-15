package alice;

public class buildsolution {
	static int m=6;
	static int n=23;
	
	static int pow;
	static int pow2 ;
	
	
	static int[][] balls;
	
	public static void main(String args[])
	{
		n=n-2;
		pow = (int)Math.pow(2,n)+1;
		pow2 = (int)Math.pow(2,n+1)+1;
		balls = new int[pow2][m+1];
		
		int i,j,k,l;
		
		int pog;
		
		int countleft;
		int countright;
		
		int noderight;
		int nodeleft;
		
		int start;
		int end=0;
		
		int tmp;
		
		int diff;
		double div;
		
		int height;
		int miss;
		
		int finmin;
		int finball=0;
		
		boolean flag,flag2;
		
		int fincount=0;
		int nbballs=4;
		
		int[][] countbig = new int[n+1][m+1];
		int[][] countsmall = new int[n+1][m+1];
		balls[1][0]=1;
		
		boolean[] tmpstrat = new boolean[70];
		boolean[][] strat = new boolean[82534486][70];
		int[] ballnodes = new int[82534486];
		int[] ballvalue = new int[82534486];
		int[] ballcount = new int[m+1];
		int stratwrite;
		
		countbig[n-1][m]=1;
		for(j=0;j<m;j++) 
		{
			countbig[n-1][j]=1;
			countsmall[n-1][j]=1;
		}
		
		for(l=0;l<n-1;l++) countbig[l][m]=1;

		for(miss=m-1;miss>=0;miss--)
		{
			for(height=n-2;height>=0;height--) 
			{
				for(i=0;i<pow2;i++) for(j=0;j<m+1;j++) balls[i][j]=0;
				balls[1][miss]=1;//useless
				balls[2][miss]=1;
				balls[2][miss+1]=1;
				balls[3][miss+1]=2;
				
				i=2;
				for(l=1;l<n-height;l++)
				{
					start = i;
					end = 2*start;
					
					for(i=start;i<end;i++)
					{
						nodeleft=2*i;
						noderight=nodeleft+1;
						countleft=0;
						countright=0;
						
						for(j=0;j<m;j++)
						{
							for(k=0;k<balls[i][j];k++)
							{
								if(countright<countleft)
								{	
										balls[noderight][j]++;
										balls[noderight][j+1]++;
										balls[nodeleft][j+1]++;
										balls[nodeleft][j+1]++;
										
										countleft+=countsmall[l+height][j];
										countright+=countbig[l+height][j];
								}
								else
								{
										balls[noderight][j+1]++;
										balls[noderight][j+1]++;
										balls[nodeleft][j]++;
										balls[nodeleft][j+1]++;
										
										countleft+=countbig[l+height][j];
										countright+=countsmall[l+height][j];
								}
							}
						}
					}
				}
				
				for(i=end;i<end+end/2;i++)
				{
					flag=false;
					for(k=0;k<=m;k++) if(balls[i][k]>0) flag=true;
					
					if(flag) countbig[height][miss]++;
					else 
					{
						tmp=(int)Math.floor(i/2);
						while(balls[tmp][m]==0 && tmp!=1)
						{
							tmp=(int)Math.floor(tmp/2);
						}
						if(tmp!=1)
						{
							balls[tmp][m]--;
							balls[i][m]=1;
							countbig[height][miss]++;
						}
					}
				}
				for(i=end+end/2;i<2*end;i++)
				{
					flag=false;
					for(k=0;k<=m;k++) if(balls[i][k]>0) flag=true;
					
					if(flag) countsmall[height][miss]++;
					else 
					{
						tmp=(int)Math.floor(i/2);
						while(balls[tmp][m]==0 && tmp!=1)
						{
							tmp=(int)Math.floor(tmp/2);
						}
						if(tmp!=1)
						{
							balls[tmp][m]--;
							balls[i][m]=1;
							countsmall[height][miss]++;
						}
					}
				}
			}
		}
		
		
		strat[0][0]=false;
		strat[0][1]=true;
		strat[0][2]=true;
		strat[0][3]=true;
		strat[0][4]=true;
		strat[0][5]=true;
		
		strat[1][0]=false;
		strat[1][1]=true;
		strat[1][2]=true;
		strat[1][3]=true;
		strat[1][4]=false;
		strat[1][5]=true;
		
		strat[2][0]=false;
		strat[2][1]=false;
		strat[2][2]=true;
		strat[2][3]=false;
		strat[2][4]=false;
		strat[2][5]=true;
		
		strat[3][0]=false;
		strat[3][1]=false;
		strat[3][2]=true;
		strat[3][3]=false;
		strat[3][4]=true;
		strat[3][5]=true;
		
		ballnodes[0]=1;
		ballnodes[1]=1;
		ballnodes[2]=1;
		ballnodes[3]=1;
		
		ballvalue[0]=1;
		ballvalue[1]=2;
		ballvalue[2]=2;
		ballvalue[3]=2;
		
		stratwrite=6;
	
		for(i=0;i<pow2;i++) for(j=0;j<m+1;j++) balls[i][j]=0;
		balls[1][1]=1;
		balls[1][2]=3;
		
		i=1;
		for(l=0;l<n;l++)
		{
			start = i;
			end = 2*start;
			
			for(i=start;i<end;i++)
			{
				nodeleft=2*i;
				noderight=nodeleft+1;
				countleft=0;
				countright=0;
				
				
				for(j=0;j<m;j++)
				{
					ballcount[j]=0;
					
					for(k=0;k<balls[i][j];k++)
					{
						if(countright<countleft)
						{	
								balls[noderight][j]++;
								balls[noderight][j+1]++;
								balls[nodeleft][j+1]++;
								balls[nodeleft][j+1]++;
								
								countleft+=countsmall[l][j];
								countright+=countbig[l][j];
							
							
						}
						else
						{
							ballcount[j]++;
								balls[noderight][j+1]++;
								balls[noderight][j+1]++;
								balls[nodeleft][j]++;
								balls[nodeleft][j+1]++;
								
								countleft+=countbig[l][j];
								countright+=countsmall[l][j];
							
						}
					}
				}
				
				
					if(i==30)
					{
						ballcount[5]+=6;
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
						
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
						
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
						
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
						
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
						
						balls[nodeleft][5]++;
						balls[nodeleft][6]--;
						balls[noderight][6]++;
						balls[noderight][5]--;
					}
				
				
					tmp=nbballs;
					for(j=0;j<nbballs;j++)
					{
						if(ballnodes[j]==i && ballvalue[j]!=6)
						{
							if(ballcount[ballvalue[j]]>0)
							{
								ballcount[ballvalue[j]]--;
								
								ballnodes[j]=nodeleft;
								strat[j][stratwrite]=false;
								strat[j][stratwrite+1]=false;
								strat[j][stratwrite+2]=false;
								
								
								ballnodes[tmp]=nodeleft;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=false;
								strat[tmp][stratwrite+1]=true;
								strat[tmp][stratwrite+2]=false;
								tmp++;
							
								ballnodes[tmp]=noderight;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=false;
								strat[tmp][stratwrite+1]=false;
								strat[tmp][stratwrite+2]=true;
								tmp++;
								
								ballnodes[tmp]=noderight;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=false;
								strat[tmp][stratwrite+1]=true;
								strat[tmp][stratwrite+2]=true;
								tmp++;
							}
							else
							{
								ballnodes[j]=noderight;
								strat[j][stratwrite]=true;
								strat[j][stratwrite+1]=true;
								strat[j][stratwrite+2]=true;
								
								
								ballnodes[tmp]=noderight;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=true;
								strat[tmp][stratwrite+1]=false;
								strat[tmp][stratwrite+2]=true;
								tmp++;
							
								ballnodes[tmp]=nodeleft;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=true;
								strat[tmp][stratwrite+1]=false;
								strat[tmp][stratwrite+2]=false;
								tmp++;
								
								ballnodes[tmp]=nodeleft;
								ballvalue[tmp]=ballvalue[j]+1;
								for(k=0;k<stratwrite;k++) strat[tmp][k]=strat[j][k];
								strat[tmp][stratwrite]=true;
								strat[tmp][stratwrite+1]=true;
								strat[tmp][stratwrite+2]=false;
								tmp++;
							}
						}
					}
					nbballs=tmp;
				
			}
			stratwrite+=3;
		}
		
		for(i=end;i<2*end;i++)
		{
			finmin=7;
			for(j=0;j<nbballs;j++)
			{
				if(ballnodes[j]==i && ballvalue[j]<finmin)
				{
					finmin=ballvalue[j];
					finball=j;
				}
			}
			
			
			
			if(finmin==7)
			{
				pog=3*22;
				if(i%2==0)
				{
					tmpstrat[pog]=false;
					tmpstrat[pog+1]=false;
					tmpstrat[pog+2]=false;
				}
				else
				{
					tmpstrat[pog]=true;
					tmpstrat[pog+1]=true;
					tmpstrat[pog+2]=true;
				}
				tmp=(int)Math.floor(i/2);
				pog-=3;
				
				while(balls[tmp][m]==0 && tmp!=1)
				{
					
					if(tmp%2==0)
					{
						tmpstrat[pog]=false;
						tmpstrat[pog+1]=false;
						tmpstrat[pog+2]=false;
					}
					else
					{
						tmpstrat[pog]=true;
						tmpstrat[pog+1]=true;
						tmpstrat[pog+2]=true;
					}
					tmp=(int)Math.floor(tmp/2);
					
					pog-=3;
				}
				if(tmp!=1)
				{
					balls[tmp][m]--;
					balls[i][m]=1;
					
					for(k=0;k<nbballs;k++)
					{
							if(ballnodes[k]==tmp && ballvalue[k]==6)
							{
								pog=pog+2;
								while(pog>=0)
								{
									tmpstrat[pog]=strat[k][pog];
									pog--;
								}
								
								ballvalue[k]=7;
								k=nbballs;
							}
					}
				}
				
				for(k=0;k<23;k++)  if(tmpstrat[3*k]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				for(k=0;k<23;k++)  if(tmpstrat[3*k+1]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				for(k=0;k<23;k++)  if(tmpstrat[3*k+2]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				System.out.println("");
			}
			else
			{
				for(k=0;k<23;k++) if(strat[finball][3*k]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				for(k=0;k<23;k++)  if(strat[finball][3*k+1]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				for(k=0;k<23;k++)  if(strat[finball][3*k+2]) System.out.print(1); else System.out.print(0);
				System.out.println("");
				System.out.println("");
			}
		}
		
		
		
		
	}
}
