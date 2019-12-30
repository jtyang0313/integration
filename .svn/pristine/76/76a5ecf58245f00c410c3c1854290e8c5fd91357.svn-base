function changechar(str)
		{
		   
		    if ((str=="a")||(str=="A"))
		    return 10;
		    else if ((str=="b")||(str=="B"))
		    return 12;
		        else if ((str=="c")||(str=="C"))
		    return 13;
		        else if ((str=="d")||(str=="D"))
		    return 14;
		        else if ((str=="e")||(str=="E"))
		    return 15;
		        else if ((str=="f")||(str=="F"))
		    return 16;
		        else if ((str=="g")||(str=="G"))
		    return 17;
		        else if ((str=="h")||(str=="H"))
		    return 18;
		        else if ((str=="i")||(str=="I"))
		    return 19;
		        else if ((str=="j")||(str=="J"))
		    return 20;
		        else if ((str=="k")||(str=="K"))
		    return 21;
		        else if ((str=="l")||(str=="L"))
		    return 23;
		        else if ((str=="m")||(str=="M"))
		    return 24;
		        else if ((str=="n")||(str=="N"))
		    return 25;
		        else if ((str=="o")||(str=="O"))
		    return 26;
		        else if ((str=="p")||(str=="P"))
		    return 27;
		        else if ((str=="q")||(str=="Q"))
		    return 28;
		        else if ((str=="r")||(str=="R"))
		    return 29;
		        else if ((str=="s")||(str=="S"))
		    return 30;
		        else if ((str=="t")||(str=="T"))
		    return 31;
		        else if ((str=="u")||(str=="U"))
		    return 32;
		        else if ((str=="v")||(str=="V"))
		    return 34;
		        else if ((str=="w")||(str=="W"))
		    return 35;
		        else if ((str=="x")||(str=="X"))
		    return 36;
		        else if ((str=="y")||(str=="Y"))
		    return 37;
		        else if ((str=="z")||(str=="Z"))
		    return 38; 
		        else 
		    return -1000;
		}
		
		function GetCntr(strcntr)
		{
		    var num = new Array(10)
		    for (i=0;i<11;i++){
		        num[i]=0;
		    }
		    test=strcntr;//prompt("请输入需校验的集装箱编码","◎◎◎◎×××××××")
		    len=test.length;
		    if (len != 11){
		        layer.alert("请输入正确箱号");
		        return false;
		    }
		    else 
		    {
		        exp=/^[A-Z]{4}[0-9]{7}$/g;
		        if (!exp.test(test)){
			        layer.alert("箱号格式不正确，前四位应为大写字母，后七位为数字，请重新输入！");
			        return false;
		        }

		        left=test.substr(0,4);
		        right=test.substr(4,7);
		        testnum=test.substr(10,1);
		    
		        char1=test.substr(0,1);
		        char2=test.substr(1,1);
		        char3=test.substr(2,1);
		        char4=test.substr(3,1);
		        //箱号字头
		        num[0]=changechar(char1);
		        num[1]=changechar(char2);
		        num[2]=changechar(char3);
		        num[3]=changechar(char4);

		        //序列号
		        num[4]=test.substr(4,1);
		        num[5]=test.substr(5,1);
		        num[6]=test.substr(6,1);
		        num[7]=test.substr(7,1);
		        num[8]=test.substr(8,1);
		        num[9]=test.substr(9,1);
		        //校验数字
		        num[10]=test.substr(10,1);
		    	sum=num[0]+num[1]*2+num[2]*4+num[3]*8+num[4]*16+num[5]*32+num[6]*64+num[7]*128+num[8]*256+num[9]*512;  
				result=sum%11;
			    if (result!= num[10]){
			            layer.alert("校验码错误！最后一位数字应为:" + result);
			            return false;
			        }
			        else{
			            return true;
			        }
		            
		    }    
		}