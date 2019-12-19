package com.thekingqj;

public class sort {
    public static void main(String[] args) {
        //int m[]={1,2,3,4,5};
        int m[]={5,4,3,2,1};
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i]+"\t");
        }
        System.out.println();
        int flag=0;

        for (int i = 0; i <m.length-1 ; i++) {

            for(int j=0;j<m.length-1;j++){
                if(m[j]>=m[j+1]){
                    int temp=m[j];
                    m[j]=m[j+1];
                    m[j+1]=temp;
                    System.out.println("第"+(++flag)+"次交换");
                }else{
                    System.out.println(++flag);
                }
            }

        }
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i]+"\t");
        }
    }
}
