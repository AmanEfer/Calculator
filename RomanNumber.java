package Task1;

enum RomanNumber {
    C(100), XC(90), L(50), XL(40), X(10),
    IX(9), VIII(8), VII(7), VI(6), V(5), IV(4), III(3), II(2), I(1);

    private int arabicValue;

    RomanNumber(int arabicValue) {
        this.arabicValue = arabicValue;
    }

    public int getArabicValue() {
        return arabicValue;
    }
}
