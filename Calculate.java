class Calculate
{
    static String aa;
    Calculate(String aa)
    {
        this.aa=aa;
    }
    public static String getString ()
    {
        StringBuffer str1=new StringBuffer(aa);
        String num1="",num2="",str="";
        int ln=0,bp1=0,bp2=0,i=0;
        for(i=0;i<str1.length()-1;i++)
        {
            char ch1=str1.charAt(i),ch2=str1.charAt(i+1);
            if((ch1=='1'||ch1=='2'||ch1=='3'||ch1=='4'||ch1=='5'||ch1=='6'||
            ch1=='7'||ch1=='8'||ch1=='9'||ch1=='0')&&
            (ch2=='('||ch2=='s'||ch2=='c'||ch2=='t'||ch2=='l'))
            {
                str1.insert(i+1,"*");
            }
        }
        for(i=0;i<str1.length()-1;i++)
        {
            char ch1=str1.charAt(i),ch2=str1.charAt(i+1);
            if((ch2=='1'||ch2=='2'||ch2=='3'||ch2=='4'||ch2=='5'||ch2=='6'||
            ch2=='7'||ch2=='8'||ch2=='9'||ch2=='0'||ch2=='s'||ch2=='c'||ch2=='t'||ch2=='l')
            &&ch1==')')
            {
                str1.insert(i+1,"*");
            }
        }
        String s=str1.toString();
        s=correcttrig(s);str1=new StringBuffer(s);
        str=str1.toString();
        if(correct(str))
        {
            str=sign(str);
            while(symbol(str))
            {
                str="("+str+")";
                ln=str.length();num1="";num2="";bp1=0;bp2=0;
                for(i=0;i<ln;i++)
                {
                    char ch=str.charAt(i);
                    if(ch=='(')
                        bp1=i;
                }
                for(i=bp1+1;i<ln;i++)
                {
                    char ch=str.charAt(i);
                    if(ch==')')
                    {
                        bp2=i;i=ln;
                    }
                }
                String bracket="";
                for(i=bp1+1;i<bp2;i++)
                {
                    bracket=bracket+str.charAt(i);
                }
                int bd0=0,bd1=0,bd2=0,bd3=0,bd4=0;
                for(i=0;i<bracket.length();i++)
                {
                    char c=bracket.charAt(i);
                    if(c=='^')
                        bd0++;
                    if(c=='/')
                        bd1++;
                    if(c=='*')
                        bd2++;
                    if(c=='+')
                        bd3++;
                    if(c=='-')
                        bd4++;
                }
                for(i=counttrig(bracket);i>=1;i--)
                {
                    bracket=dotrig(bracket);
                    bracket=sign(bracket);
                }
                for(bd0=bd0;bd0>=0;bd0--)
                {
                    bracket=power(bracket);
                    bracket=sign(bracket);
                }
                for(bd1=bd1;bd1>=0;bd1--)
                {
                    bracket=divide(bracket);
                    bracket=sign(bracket);
                }
                for(bd2=bd2;bd2>=0;bd2--)
                {
                    bracket=multiply(bracket);
                    bracket=sign(bracket);
                }
                for(bd3=bd3;bd3>=0;bd3--)
                {
                    bracket=add(bracket);
                    bracket=sign(bracket);
                }
                for(bd4=bd4;bd4>=0;bd4--)
                {
                    bracket=subtract(bracket);
                    bracket=sign(bracket);
                }
                for(i=0;i<bp1;i++)
                {
                    char ch=str.charAt(i);
                    num1=num1+ch;
                }
                for(i=ln-1;i>bp2;i--)
                {
                    char ch=str.charAt(i);
                    num2=ch+num2;
                }
                str=num1+bracket+num2;
                str=sign(str);
            }
            return str;
        }
        return "Syntax Error";
    }
    static String calc(String m1,String sign,String m2)
    {
        String answer="";
        if((m1.equals("-inf")&&m2.equals("-inf"))||
            ((m1.equals("inf")||(m1.equals("+inf")))&&(m2.equals("inf")||(m2.equals("+inf"))))||
            (m1.equals("-inf")&&(m2.equals("inf")||(m2.equals("+inf"))))||
            ((m1.equals("inf")||(m1.equals("+inf")))&&m2.equals("-inf"))||
            m1.equals("inf")||m2.equals("inf")||m1.equals("+inf")||m2.equals("+inf")||
            m1.equals("-inf")||m2.equals("-inf")    )
            return "+inf";
        if(sign.equals("+"))
        {
            if(m1.equals("inf") || m2.equals("inf"))
                answer="+inf";
            else
                answer=""+(Double.parseDouble(m1)+Double.parseDouble(m2));
        }
        else if (sign.equals("-"))
            if (m1.equals("inf") || m2.equals("inf"))
                answer="+inf";
            else
                answer=""+(Double.parseDouble(m1)-Double.parseDouble(m2));
        else if (sign.equals("*"))
        {
            if (m1.equals("inf") && m2.equals("inf") )
                answer="+inf";
            else if (((m1).equals("0.0") && m2.equals("inf")) ||(m1.equals("inf")&&(m2).equals("0.0")))
                        answer="1";
            else if( m1.equals("inf") || m2.equals("inf"))
                answer="+inf";
            else
                answer=""+(Double.parseDouble(m1)*Double.parseDouble(m2));
        }
        else if (sign.equals("/"))
        {
            if ((m1).equals("1") && m2.equals("inf"))
                answer="+inf";
            else if (((m1).equals("1") && (m2).equals("0.0"))|| (m1.equals("inf") && m2.equals("inf")))
                answer="+inf";
            else if ((m1).equals("0.0")&& (m2).equals("0.0"))
                answer="+inf";
            else if( m1.equals("inf")|| m2.equals("inf"))
                answer="+inf";
            else
                answer=""+(Double.parseDouble(m1)/Double.parseDouble(m2));
        }
        else if (sign.equals("^"))
        {
            if (m2.equals("inf") || m1.equals("inf"))
                answer="inf";
            else if( (m1).equals("0.0") && (m2).equals("0.0"))
                answer="+inf";
            else
                answer=""+Math.pow(Double.parseDouble(m1),Double.parseDouble(m2));
        }
        else
            answer="0";
        return answer;
    }
    static String multiply(String str)
    {
        int i=0,ln=str.length(),mm=0,j=0,y=0;String m1="",m2="",num1="",num2="",num="";
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='*')
            {
                mm=i;i=ln;
            }
        }
        if(mm==0)
            return str;
        for(i=mm-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='^')
            {
                for(j=0;j<=i;j++)
                {
                    char ch1=str.charAt(j);
                    num1=num1+ch1;
                }
                i=0;
            }
            else if(ch=='-')
            {
                int jj=0;
                for(jj=i;jj>=0;jj--)
                {
                    char c=str.charAt(jj);y=1;
                    if(c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'&&c!='0'&&c!='f')
                    {
                        m1=c+m1;
                    }
                    else
                        break;
                }
                for(jj=jj;jj>=0;jj--)
                {
                    char c=str.charAt(jj);
                    num1=c+num1;
                }
                i=0;
            }
            else
            {
                if(y==0)
                    m1=ch+m1;
            }
        }
        for(i=mm+1;i<ln;i++)
        {
            char ch=str.charAt(i);
            if((ch=='-'||ch=='+')&&nodigit(m2))
                m2=m2+ch;
            else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
            {
                for(j=i;j<ln;j++)
                {
                    char ch1=str.charAt(j);
                    num2=num2+ch1;
                }
                i=ln;
            }
            else
            {
                m2=m2+ch;
            }
        }
        String m3=calc(m1,"*",m2);
        str=num1+"+"+m3+num2;
        return str;
    }
    static String add(String str)
    {
        int i=0,ln=str.length(),mm=0,j=0;
        String m1="",m2="",num1="",num2="";int y=0;      
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='+'&&i>0)
            {
                mm=i;i=ln;
            }
        }
        if(mm==0)
            return str;
        for(i=mm-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='^')
            {
                for(j=0;j<=i;j++)
                {
                    char ch1=str.charAt(j);
                    num1=num1+ch1;
                }
                i=0;
            }
            else if(ch=='-'&&nodigit(m1))
                i=0;
            else if(ch=='-')
            {
                int jj=0;
                for(jj=i;jj>=0;jj--)
                {
                    char c=str.charAt(jj);y=1;
                    if(c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'&&c!='0')
                    {
                        m1=c+m1;
                    }
                    else
                        break;
                }
                for(jj=jj;jj>=0;jj--)
                {
                    char c=str.charAt(jj);
                    num1=c+num1;
                }
                i=0;
            }
            else
            {
                if(y==0)
                m1=ch+m1;
            }
        }
        for(i=mm+1;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='-'&&nodigit(m2))
                m2=m2+ch;
            else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
            {
                for(j=i;j<ln;j++)
                {
                    char ch1=str.charAt(j);
                    num2=num2+ch1;
                }
                i=ln;
            }
            else
            {
                m2=m2+ch;
            }
        }
        String m3=calc(m1,"+",m2);
        str=num1+"+"+m3+num2;
        return str;
    }
    static String subtract(String str)
    {
        int i=0,ln=str.length(),mm=0,j=0;String m1="",m2="",num1="",num2="",num="";
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='-'&&i>0)
            {
                mm=i;i=ln;
            }
        }
        if(mm==0)
            return str;
        for(i=mm-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='^')
            {
                for(j=0;j<=i;j++)
                {
                    char ch1=str.charAt(j);
                    num1=num1+ch1;
                }
                i=0;
            }
            else
            {
                m1=ch+m1;
            }
        }
        for(i=mm+1;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='-'&&nodigit(m2))
                m2=m2+ch;
            else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
            {
                for(j=ln-1;j>=i;j--)
                {
                    char ch1=str.charAt(j);
                    num2=ch1+num2;
                }
                i=ln;
            }
            else
            {
                m2=m2+ch;
            }
        
        }
        String m3=calc(m1,"-",m2);
        str=num1+"+"+m3+num2;
        return str;
    }
    static boolean nodigit(String str)
    {
        int ln=str.length(),i,gh=0;
        if(str.length()==0)
            return true;
        if(str.charAt(0)=='i')
            return false;
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9'||ch=='0')
                gh=1;
        }
        if(gh==1)
            return false;
        else
            return true;
    }
    static String divide(String str)
    {
        int i=0,ln=str.length(),mm=0,j=0,y=0;String m1="",m2="",num1="",num2="",num="";
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='/')
            {
                mm=i;i=ln;
            }
        }
        if(mm==0)
            return str;
        for(i=mm-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='^')
            {
                for(j=0;j<=i;j++)
                {
                    char ch1=str.charAt(j);
                    num1=num1+ch1;
                }
                i=0;
            }
            else if(ch=='-')
            {
                int jj=0;
                for(jj=i;jj>=0;jj--)
                {
                    char c=str.charAt(jj);y=1;
                    if(c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'&&c!='0')
                    {
                        m1=c+m1;
                    }
                    else
                        break;
                }
                for(jj=jj;jj>=0;jj--)
                {
                    char c=str.charAt(jj);
                    num1=c+num1;
                }
                i=0;
            }
            else
            {
                if(y==0)
                    m1=ch+m1;
            }
        }
        for(i=mm+1;i<ln;i++)
        {
            char ch=str.charAt(i);
            if((ch=='-'||ch=='+')&&nodigit(m2))
                m2=m2+ch;
            else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
            {
                for(j=i;j<ln;j++)
                {
                    char ch1=str.charAt(j);
                    num2=num2+ch1;
                }
                i=ln;
            }
            else
            {
                m2=m2+ch;
            }
        }
        String m3=calc(m1,"/",m2);
        str=num1+"+"+m3+num2;
        return str;
    }
    static String power(String str)
    {
        int i=0,ln=str.length(),mm=0,j=0,y=0;String m1="",m2="",num1="",num2="",num="";
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='^')
            {
                mm=i;i=ln;
            }
        }
        if(mm==0)
            return str;
        for(i=mm-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='^')
            {
                for(j=0;j<=i;j++)
                {
                    char ch1=str.charAt(j);
                    num1=num1+ch1;
                }
                i=0;
            }
            else if(ch=='-')
            {
                int jj=0;
                for(jj=i;jj>=0;jj--)
                {
                    char c=str.charAt(jj);y=1;
                    if(c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'&&c!='0')
                    {
                        m1=c+m1;
                    }
                    else
                        break;
                }
                for(jj=jj;jj>=0;jj--)
                {
                    char c=str.charAt(jj);
                    num1=c+num1;
                }
                i=0;
            }
            else
            {
                if(y==0)
                    m1=ch+m1;
            }
        }
        for(i=mm+1;i<ln;i++)
        {
            char ch=str.charAt(i);
            if((ch=='-'||ch=='+')&&nodigit(m2))
                m2=m2+ch;
            else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
            {
                for(j=i;j<ln;j++)
                {
                    char ch1=str.charAt(j);
                    num2=num2+ch1;
                }
                i=ln;
            }
            else
            {
                m2=m2+ch;
            }
        }
        String m3=calc(m1,"^",m2);
        str=num1+"+"+m3+num2;
        return str;
    }
    static boolean symbol(String str)
    {
        int i,ln=str.length(),sy=0;char ch1,ch2,ch3;
        for(i=0;i<ln;i++)
        {
            char ch=str.charAt(i);
            if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^')
                sy++;
        }
        for(i=0;i<str.length()-2;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);ch3=str.charAt(i+2);
            if((ch1=='s'&&ch2=='i'&&ch3=='n')||
            (ch1=='c'&&ch2=='o'&&ch3=='s')||
            (ch1=='l'&&ch2=='o'&&ch3=='g')||
            (ch1=='t'&&ch2=='a'&&ch3=='n'))
                return true;
        }
        if(str.equals(""))
            return false;
        else if(sy==1&&(str.charAt(0)=='-'||str.charAt(0)=='+'))
            return false;
        else if(sy>0)
            return true;
        else if(sy==0&&str.charAt(0)!='+'&&str.charAt(0)!='-'&&
            str.charAt(0)!='*'&&str.charAt(0)!='/'&&str.charAt(0)!='^')
            return false;
        else
            return false;
    }
    static boolean isAlpha(String name) 
    {
        return name.matches("[a-zA-Z]+");
    }
    static boolean correct(String str)
    {
        int ln=str.length(),i,bs=0,bt=0,s=0,j,bp1=0,bp2=0;;
        char ch,ch1,ch2,ch3;str="("+str+")";
        for(i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if(ch!='+'&&ch!='-'&&ch!='/'&&ch!='*'&&ch!='.'&&ch!='s'&&ch!='c'&&ch!='i'&&ch!='n'&&ch!='o'&&ch!='s'&&ch!='t'&&ch!='a'&&ch!='l'&&ch!='g'
                &&ch!='('&&ch!=')'&&ch!='^'&&!Character.isDigit(ch)  )
                return false;
        }
        for(i=0;i<str.length()-1;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);
            if(ch1=='('&&ch2==')')
            {
                //System.out.println("Syntax Error1");
                return false;
            }
        }
        for(i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if(ch=='(')
                bp2++;
            if(ch==')')
                bp1++;
        }
        if(bp1!=bp2)
        {
            //System.out.println("Syntax Error2");
            return false;
        }
        int b1[]=new int[bp1];
        int b2[]=new int[bp2];bp1=0;bp2=0;
        for(i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if(ch==')')
            {
                b2[bp2]=i;bp2++;
            }
            if(ch=='(')
            {
                b1[bp1]=i;bp1++;
            }
        }
        for(i=0;i<bp2;i++)
        {
            for(j=b2[i];j>=0;j--)
            {
                ch=str.charAt(j);
                if(ch=='(')
                {
                    for(int k=0;k<bp1;k++)
                    {
                        if(b1[k]==j)
                        {
                            b1[k]=-1;b2[i]=-1;k=bp1;j=0;
                        }
                    }
                }
            }
        }
        for(i=0;i<bp2;i++)
        {
            if(b1[i]!=-1||b2[i]!=-1)
            {
                //System.out.println("Syntax Error");
                return false;
            }
        }
        for(i=0;i<ln-1;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);
            if(((ch1=='*'||ch1=='/'||ch1=='^')&&
                (ch2=='*'||ch2=='/'||ch2=='^'))||
                ((ch1=='-'||ch1=='+'||ch1=='^')&&(ch2=='*'||ch2=='/'||ch2=='^')))
                s=1;
        }
        for(i=1;i<str.length()-3;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);ch3=str.charAt(i+2);j=0;
            if(isAlpha(ch1+""))
            { 
                if(ch1=='s'&&ch2=='i'&&ch3=='n')
                    j=1;
                else if(ch1=='c'&&ch2=='o'&&ch3=='s')
                    j=1;
                else if(ch1=='t'&&ch2=='a'&&ch3=='n')
                    j=1;
                else if(ch1=='l'&&ch2=='o'&&ch3=='g')
                    j=1;
                else if(ch1=='i'&& ch2=='n'&& ch3=='f')
                    j=1;
                else
                    s=1;
            }
            else
            {}
            if(j==0)
            {}
            else
                i+=2;
        }
        ch1=str.charAt(1);
        if(ch1=='*'||ch1=='/'||ch1=='^')
            s=1;
        for(i=str.length()-1;i>=0;i--)
        {
            ch1=str.charAt(i);
            if(ch1!=')')
                break;
        }
        if(s!=0||ch1=='+'||ch1=='-'||ch1=='*'||ch1=='/'||ch1=='^')
        {
            //System.out.println("Syntax Error");
            return false;
        }
        return true;
    }
    static String sign(String str)
    {
        str="("+str+")";
        for(int i=1;i<str.length();i++)
        {
            if(str.charAt(i-1)=='('&&str.charAt(i)=='+')
            {
                StringBuffer temp=new StringBuffer(str);
                temp.delete(i,i+1);
                str=temp.toString();
            }
        }
        int ln,i,j,a,bp1=0,bp2=0;char ch1,ch2;String num1="",num2="",num="";
        for(j=str.length()-1;j>=0;j-=2)
        {
            bp1=0;bp2=0;num1="";num2="";num="";
            for(i=0;i<str.length();i++)
            {
                ch1=str.charAt(i);
                if(ch1=='(')
                {
                    bp1=i+1;
                }
            }    
            for(i=bp1;i<str.length();i++)
            {
                ch1=str.charAt(i);
                if(ch1==')')
                {
                    bp2=i-1;i=str.length();
                }
            } 
            if(bp2==0)
                bp2=-2;
            for(i=0;i<bp1-1;i++)
            {
                ch1=str.charAt(i);
                num1=num1+ch1;
            }
            for(i=bp2+2;i<str.length();i++)
            {
                ch1=str.charAt(i);
                num2=num2+ch1;
            }
            num="";
            for(i=bp1;i<=bp2;i++)
            {
                ch1=str.charAt(i);
                num=num+ch1;
            }
            if(symbol(num))
            {}
            else
            {
                str=num1+num+num2;
            }
        }
        for(j=0;j<str.length()/2;j++)
        {
            for(i=0;i<str.length()-1;i++)
            {
                num1="";num2="";
                for(a=0;a<i;a++)
                {
                    num1=num1+str.charAt(a);
                }
                for(a=i+2;a<str.length();a++)
                {
                    num2=num2+str.charAt(a);
                }
                ch1=str.charAt(i);ch2=str.charAt(i+1);
                if(ch1=='+'&&ch2=='+')
                    str=num1+"+"+num2;
                else if(ch1=='-'&&ch2=='+')
                    str=num1+"-"+num2;
                else if(ch1=='+'&&ch2=='-')
                    str=num1+"-"+num2;
                else if(ch1=='-'&&ch2=='-')
                    str=num1+"+"+num2;
                else if(ch1==')'&&ch2=='(')
                    str=num1+"*"+num2;
                else
                    str=str;
            }
        }
        for(i=1;i>0;i++)
        {
            ch1=str.charAt(0);ch2=str.charAt(str.length()-1);
            if(ch1=='('&&ch2==')')
            {
                StringBuffer ss=new StringBuffer(str);
                ss.delete(0,1);ss.delete(ss.length()-1,ss.length());
                str=ss.toString();
            }
            else
                break;
        }
        return str;
    }
    static String dotrig(String str)
    {
        int ln=str.length(),found=0;String m1="",num1="",num2="",m2="";
        char ch1,ch2,ch3,ch;String ff="";
        for(int i=0;i<ln-2;i++)
        {
            ch1=str.charAt(i);
            ch2=str.charAt(i+1);
            ch3=str.charAt(i+2);
            if((ch1=='s'&&ch2=='i'&&ch3=='n')||
            (ch1=='c'&&ch2=='o'&&ch3=='s')||
            (ch1=='l'&&ch2=='o'&&ch3=='g')||
            (ch1=='t'&&ch2=='a'&&ch3=='n'))
            {
                found=1;
                if(ch1=='s'&&ch2=='i'&&ch3=='n')
                    ff="sin";
                if(ch1=='c'&&ch2=='o'&&ch3=='s')
                    ff="cos";
                if(ch1=='t'&&ch2=='a'&&ch3=='n')
                    ff="tan";
                if(ch1=='l'&&ch2=='o'&&ch3=='g')
                    ff="log";
                for(int j=i+3;j<ln;j++)
                {
                    ch=str.charAt(j);
                    if((ch=='+'||ch=='-')&&nodigit(m1))
                        m1=m1+ch;
                    else if(ch=='+'||ch=='/'||ch=='*'||ch=='('||ch==')'||ch=='-'||ch=='^')
                    {
                        for(int h=j;h<ln;h++)
                        {
                            char h1=str.charAt(h);
                            num2=num2+h1;
                        }
                        i=ln;j=ln;
                    }
                    else
                        m1=m1+ch;
                }
                i=ln;
            }
            else
                num1=num1+ch1;
        }
        if(found==1)
        {
            double bb=Double.parseDouble(m1);
            if(ff.equals("sin"))
            {
                bb=Math.toRadians(bb);
                bb=Math.sin(bb);
                bb=(double)Math.round(bb*1000000000)/1000000000;
            }
            if(ff.equals("cos"))
            {
                bb=Math.toRadians(bb);
                bb=Math.cos(bb);
                bb=(double)Math.round(bb*1000000000)/1000000000;
            }
            if(ff.equals("tan"))
            {
                char ssy;
                if(bb<0)
                {
                    ssy='-';
                    bb=-2*bb+bb;
                }
                else
                    ssy='+';
                try
                {
                    bb=tan(bb);
                    if(ssy=='-')
                        bb=bb-2*bb;
                }
                catch(Exception e)
                {
                   return num1+"inf"+num2;
                }
                bb=(double)Math.round(bb*1000000000)/1000000000;
            }
            if(ff.equals("log"))
            {
                bb=Double.parseDouble(m1);bb=Math.log10(bb);
            }
            str=num1+bb+num2;
        }
        return str;   
    }
    static double tan(double bb)
    {
        while(bb>180)
            bb-=180;
        bb=Math.toRadians(bb);
        double g=Math.toRadians(90);
        if(bb==g)
            throw null;
        else
            return Math.tan(bb);
    }
    static String correcttrig(String s)
    {
        StringBuffer str=new StringBuffer(s);
        char ch1,ch2,ch3;str.insert(0,"(");str.insert(str.length(),")");
        int i;
        for(i=0;i<str.length()-2;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);ch3=str.charAt(i+2);
            if((ch1=='s'&&ch2=='i'&&ch3=='n')||
            (ch1=='c'&&ch2=='o'&&ch3=='s')||
            (ch1=='l'&&ch2=='o'&&ch3=='g')||
            (ch1=='t'&&ch2=='a'&&ch3=='n'))
            {
                if(str.charAt(i+3)=='(')
                    continue;
                str.insert(i+3,"(");
                for(int j=i+4;j<str.length();j++)
                {
                   char ch=str.charAt(j);
                   if((ch=='-'||ch=='+')&&j==i+4)
                        continue;
                   if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch==')'
                   ||ch=='^')
                   {
                       str.insert(j,")");
                       j=str.length();
                   }
                }
            }
        }
        str.delete(0,1);
        str.delete(str.length()-1,str.length());
        s=str.toString();
        return s;
    }
    static int counttrig(String str)
    {
        int ln=str.length(),i,count=0;char ch1,ch2,ch3;
        for(i=0;i<ln-2;i++)
        {
            ch1=str.charAt(i);ch2=str.charAt(i+1);ch3=str.charAt(i+2);
            if((ch1=='s'&&ch2=='i'&&ch3=='n')||
            (ch1=='c'&&ch2=='o'&&ch3=='s')||
            (ch1=='l'&&ch2=='o'&&ch3=='g')||
            (ch1=='t'&&ch2=='a'&&ch3=='n'))
                count++;
        }
        return count;
    }
}
