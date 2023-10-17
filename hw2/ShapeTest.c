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

#define GET_NAME_INDEX 0
#define GET_AREA_INDEX 1
#define PRINT_INDEX 2
#define DRAW_INDEX 3

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
    char* name;
    int base;
    int height;
};
//triangle functions

double getArea(Triangle* _this){
    double area = (double)_this->base*(double)_this->height / 2;
    return area;
}

VTableUnit Triangle_VTable [] = {
    {.charMethod=(char_method_type)getName},
    {.doubleMethod=(double_method_type)getArea}
};

Triangle* Triangle_Constructor(Triangle* _this, char* name, int base, int height){
    _this->vPtr = Triangle_VTable;
    _this->name = name;
    _this->base = base;
    _this->height = height;
    return _this;
}

//

int main(){
    char name[] = "Shape";
    Shape* a = Shape_Constructor((Shape*)malloc(sizeof(Shape)), name);
    char* nameFinal = a->vPtr[GET_NAME_INDEX].charMethod(a);
    printf("%s\n", nameFinal);

    char triName[] = "Triangle";
    int base = 5;
    int height = 5;
    Triangle* tri = Triangle_Constructor((Triangle*)malloc(sizeof(Shape)), triName, base, height);

    printf("%f\n", tri->vPtr[GET_AREA_INDEX].doubleMethod(tri));
    printf("%s\n", tri->vPtr[GET_NAME_INDEX].charMethod((Shape*)tri));
}

