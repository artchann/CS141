#include "vector.h"

int main() // Here is a start:

{

        Vector<int> intVec{1,3,5,7,9};

        Vector<double> doubleVec{1.5,2.5,3.5,4.5};
        
        intVec.printAll();
        doubleVec.printAll();

        Vector<int> duplicateInt(intVec);
        duplicateInt.printAll();
        duplicateInt[4] = 1;
        std::cout << "testing fourth index mutation (needs 1):" << duplicateInt[4] << std::endl;
        int testMe = duplicateInt[1];
        std::cout << "testing const on first index:" <<testMe << std::endl;
        //Vector<int> iv{intVec};

        //Vector<double> dv{doubleVec};
        
        //intVec.printAll();

      //  cout << "intVec" << intVec << endl;

// "intVec(1, 3, 5, 7, 9)"

        //cout << "iv" << iv << endl;

// "iv(1, 3, 5, 7, 9)"

        //cout << "doubleVec" << doubleVec << endl;

// "doubleVec(1.5, 2.5, 3.5, 4.5)"

        //cout << "dv" << dv << endl;


// "dv(1.5, 2.5, 3.5, 4.5)"


        // add at least one test case for each method defined in Vector

return 0;

}
