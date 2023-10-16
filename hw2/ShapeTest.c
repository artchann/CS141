#include <malloc.h>
#include <iostream>
#include <cstring>

using namespace std;

typedef double (*double_method_type)(void *);
typedef void (*void_method_type)(void *);
typedef char* (*char_method_type)(void*);

typedef union{
    double_method_type doubleMethod;
    void_method_type voidMethod;
    char_method_type charMethod;
}VTableUnit;


struct Shape{
    VTableUnit* vPtr;
    char* name;
};

//Shape functions
char* getName(Shape* _this){
    //will return original copy... best practice is malloc here?
    return _this->name;
}

VTableUnit Shape_VTable [] = {
    {.charMethod=(char_method_type)getName}
};

Shape* Shape_Constructor(Shape* _this, char* name){
    _this->vPtr = Shape_VTable;
    _this->name = name;
    return _this;
}
//

struct Triangle {
    VTableUnit* vPtr;
    double base;
    double height;
};

int main(){
    char name[] = "Shape";
    Shape* a = Shape_Constructor((Shape*)malloc(sizeof(Shape)), name);
    char* nameFinal = a->vPtr[0].charMethod(a);
    printf("%s\n", nameFinal);
}

