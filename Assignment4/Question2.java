package com.company;

public class Question2 {

    public static void main(String[] args) {
        int val = -1;
        byte byteVal = (byte) val;
        char charVal = (char) byteVal; //Manual type Casting because they are not Compatible.
        int finalReturnedVal = charVal;
        //32 bits -> 8 bits -> 16 bits -> 32 bits.
        System.out.println(val+"->"+byteVal+"->"+charVal+"->"+finalReturnedVal);
        // -1 65535

        /*
        Ans:
        int to byte(Narrowing Casting(Manual)) -
            While assigning value to byte type the fractional part is lost and is reduced to modulo 256(range of byte).
            Range of byte [-255,256]. So, -1 lies in its range hence no change in value.
        byte to char(Widening Casting(Manual)) -
            A 'char' in Java is a Unicode code-unit which is treated as an unsigned number.
            So when we perform the conversion the value is 2^16 - 1 or 65536 - 1.(first converted to int then dropdown to char)
        char to int(Widening Casting(Automatic)) -
            direct conversion to 65535.

        To Solve this problem either explicitly encode and decode when using char or use
        CharsetEncoder and CharsetDecoder methods.
         */
    }
}
