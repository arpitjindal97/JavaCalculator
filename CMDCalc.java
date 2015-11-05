import java.io.*;
class CMDCalc
{
    public static void main(String args[])throws IOException,Exception
    {
        InputStreamReader rd=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(rd);
        System.out.print('\f');
        System.out.println("Enter the expression");
        String str=br.readLine(),st="";
        do
        {  
            str=str+st;
            if(!st.equals(""))
            {
                System.out.print('\f');
                System.out.println(str);
            }
            Calculate t=new Calculate(str);
            str=t.getString();
            if(str.equals("Syntax Error")||str.equals("inf"))
            {
                System.out.println(str);
                return;
            }
            else
                System.out.print(str);
            st=br.readLine();
            if(st.length()==0)
                return;
        }while(st.length()!=0);
    }
}
