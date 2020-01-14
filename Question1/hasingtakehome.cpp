#include <iostream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>

int make_array();
int hastable();
bool get_sum(int);

int data[100000],has[100003];
using namespace std;

int main() {
	make_array(); 
	hastable();
/*	for(int i=0;i<100000;i++){
		printf("has[%d] = %d\n",i,has[i]);
	}*/
	int sum1[]={231552, 234756, 596873, 648219, 726312, 981237,
				988331, 1277361, 1283379};
	for(int i=0;i<9;i++){
		get_sum(sum1[i]);
	}
	
}

//getting the data from the text file
int make_array(){
	
	 int i,j,a;

    std::ifstream input("data.txt");

    for (int i = 0; i < 100000; i++) {
        input >> data[i];
      //  printf("%d\n",data[i]);
        }
        
    /*	for (i=0;i<100000;i++){
		 for (j=i+1;j<=100000;++j){
		 	if(data[i]>data[j]){
		 		a=data[i];
		 		data[i]=data[j];
		 		data[j]=a;
			 }
		 }
	}
	for(i=0;i<100000;i++){
		printf("data[%d] = %d\n",i,data[i]);
	}
	}*/
}

int hastable(){
	for(int i = 0; i < 100003 ; i++){
		has[i]=0;
	}
	for(int b = 0;b < 100000; b++){
		int y = data[b];
		int index = y % 100003;
		
		if(has[index]==0){
			has[index]=y;
		}
		else{
			while(has[index]!=0){
				index++;
			}
			has[index]=y;
		}
	//	printf("hash[%d] = %d\n",b,hash[index]);
	}
		
}

bool get_sum(int sum){	
	int x = sum;
	for(int z =0;z<100003;z++){
		int a = x-has[z];
		int index = a %100003;
			if(has[index]==a){
				printf("1\t");
				return true;
			}
			else{
				for(index+1
				;index<100003;index++){
					if(has[index]+has[z]==x){
						printf("1\t");
						return true;
					}
					
				}
			}
		/*	else{
				while(has[index]+has[z]!=x){
					index++;
						if(has[index]+has[z]==x){
							printf("1\t");
						}
						else
							printf("0\t");
				}
				
			}*/
		
	}

	printf("0\t");
	return false;
}

