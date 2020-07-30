package am.job.rest.util;
class Test2 {
    static int N(Integer x) {
        return x;
    }
}

 class Test {
    static int M(Integer v){
        return v;
    }
    public static void main(String[] args){
        Integer x = M(100);     // 1
        Integer z = Test2.N(100);     // 2
        System.out.println(x + " " + z);
    }
}