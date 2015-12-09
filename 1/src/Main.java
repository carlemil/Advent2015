/**
 * Created by carlemil on 2015-12-09.
 */
public class Main {

    public static void main(String[] args) {
        String s = Input.data;
        int l=0, r=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                l++;
            }else{
                r++;
            }
            if(l - r==-1){
                System.out.println(i);
                break;
            }
        }
        System.out.println(l-r);
    }


}
