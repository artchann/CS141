#include "vector.h"

int main() // Here is a start:

{

        Vector<int> intVec{1,3,5,7,9};

        Vector<double> doubleVec{1.5,2.5,3.5,4.5};
        
        std::cout << "Original Values:" << std::endl;
        intVec.printAll();
        doubleVec.printAll();

        Vector<int> duplicateInt(intVec);
        duplicateInt.printAll();
        duplicateInt[4] = 1;
        
        std::cout << "testing fourth index mutation (needs 1):" << duplicateInt[4] << std::endl;
        
        int testMe = duplicateInt[1];
        std::cout << "testing const access on first index (needs 3):" <<testMe << std::endl;
        
        Vector<int> dotOne{1,2,3};
        Vector<int> dotTwo{2,3,4};
        
        std::cout << "Should print 2 + 6 + 12 (20): " << dotOne * dotTwo << std::endl;

        Vector<int> dotThree{1,2,3};
        Vector<int> dotFour{4,5};

        std::cout << "Should print 4 + 10 (14): " << dotThree* dotFour << std::endl;
        std::cout << "Reversed dot product, 10 + 4 (14): " << dotFour*dotThree << std::endl;
        
        Vector<int> addVector = dotThree + dotFour;
        std::cout << "Adding 1 2 3 & 4 5: " << std::endl;
        addVector.printAll();
        //ERROR HERE: LARGER SZ VECTOR PERSISTS
        //DETERMINE IF FROM + OR = OPERATOR OVERLOAD
        addVector = dotFour + dotThree;
        
        //Vector<int> addVector1{4,5};
        //addVector1 = addVector1 + dotThree;
        

        std::cout << "Adding 4 5 & 1 2 3: " << std::endl;
        addVector.printAll();
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
