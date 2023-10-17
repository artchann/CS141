#include <malloc.h>
#include <iostream>
#include <cstring>

using namespace std;

typedef double (*double_method_type)(void *);
typedef void (*void_method_type)(void *);
typedef char* (*char_method_type)(void *);

typedef union{
    double_method_type doubleMethod;
    void_method_type voidMethod;
    char_method_type charMethod;
}VTableUnit;

#define GET_NAME_INDEX 0
#define GET_AREA_INDEX 1
#define PRINT_INDEX 2
#define DRAW_INDEX 3
#define PI 3.14159


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

double getTriArea(Triangle* _this){
    double area = (double)_this->base*(double)_this->height / 2;
    return area;
}

void printTri(Triangle* _this){
    printf("%s",_this->vPtr[GET_NAME_INDEX].charMethod((Shape*)_this) );
    printf("(");
    printf("%i", _this->base);
    printf(", ");
    printf("%i", _this->height);
    printf(") : ");
    printf("%f\n",_this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
}

void drawTri(Triangle* _this){
    printf("   *   \n");
    printf("  * *  \n");
    printf(" *   * \n");
    printf("*******\n");
}

VTableUnit Triangle_VTable [] = {
    {.charMethod=(char_method_type)getName},
    {.doubleMethod=(double_method_type)getTriArea},
    {.voidMethod=(void_method_type)printTri},
    {.voidMethod=(void_method_type)drawTri}
};

Triangle* Triangle_Constructor(Triangle* _this, char* name, int base, int height){
    Shape_Constructor((Shape*)_this, name);
    _this->vPtr = Triangle_VTable;
    _this->base = base;
    _this->height = height;
    return _this;
}
//

struct Circle{
    VTableUnit* vPtr;
    char* name;
    int radius;
};

//circle functions
double getCircArea(Circle* _this){
    return _this->radius*_this->radius*PI;
}

void printCirc(Circle* _this){
    printf("%s", _this->vPtr[GET_NAME_INDEX].charMethod((Shape*)_this));
    printf("("); 
    printf("%i", _this->radius);
    printf(") : ");
    printf("%f\n", _this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
}

void drawCirc(Circle* _this){
    printf("    ***    \n");
    printf("  *     *  \n");
    printf(" *       * \n");
    printf(" *       * \n");
    printf("  *     *  \n");
    printf("    ***    \n");
}

VTableUnit Circle_VTable [] = {
    {.charMethod = (char_method_type)getName},
    {.doubleMethod = (double_method_type)getCircArea},
    {.voidMethod = (void_method_type)printCirc},
    {.voidMethod = (void_method_type)drawCirc}
};

Circle* Circle_Constructor(Circle* _this, char* name, int radius){
    Shape_Constructor((Shape*)_this, name);
    _this->vPtr = Circle_VTable;
    _this->radius = radius;
    return _this;
}
//

struct Square{
    VTableUnit* vPtr;
    char* name;
    int length;
};
//square functions
double getSquareArea(Square* _this){
    return (double)_this->length*(double)_this->length;
}

void printSquare(Square* _this){
    printf("%s", _this->vPtr[GET_NAME_INDEX].charMethod((Shape*)_this));
    printf("(");
    printf("%i", _this->length);
    printf(") : ");
    printf("%f\n", _this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
}

void drawSquare(Square* _this){
    printf("*****\n");
    printf("*   *\n");
    printf("*   *\n");
    printf("*****\n"); 
}

VTableUnit Square_VTable[] = {
    {.charMethod = (char_method_type)getName},
    {.doubleMethod = (double_method_type)getSquareArea},
    {.voidMethod = (void_method_type)printSquare},
    {.voidMethod = (void_method_type)drawSquare},
};

Square* Square_Constructor(Square* _this, char* name, int length){
    Shape_Constructor((Shape*)_this, name);
    _this->vPtr = Square_VTable;
    _this->length = length;
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
    Triangle* tri = Triangle_Constructor((Triangle*)malloc(sizeof(Triangle)), triName, base, height);

    printf("%s\n", tri->vPtr[GET_NAME_INDEX].charMethod((Shape*)tri));
    printf("%f\n", tri->vPtr[GET_AREA_INDEX].doubleMethod(tri));
    tri->vPtr[PRINT_INDEX].voidMethod(tri);
    tri->vPtr[DRAW_INDEX].voidMethod(tri);

    char circName[] = "Circle";
    int radius = 4;
    Circle* circ = Circle_Constructor((Circle*)malloc(sizeof(Circle)), circName, radius);
    printf("%s\n", circ->vPtr[GET_NAME_INDEX].charMethod((Shape*)circ));
    printf("%f\n", circ->vPtr[GET_AREA_INDEX].doubleMethod(circ));
    circ->vPtr[PRINT_INDEX].voidMethod(circ);
    circ->vPtr[DRAW_INDEX].voidMethod(circ);

    char sqName[] = "Square";
    int length = 5;
    Square* sq = Square_Constructor((Square*)malloc(sizeof(Square)), sqName, length);
    printf("%s\n", sq->vPtr[GET_NAME_INDEX].charMethod((Shape*)sq));
    printf("%f\n", sq->vPtr[GET_AREA_INDEX].doubleMethod(sq));
    sq->vPtr[PRINT_INDEX].voidMethod(sq);
    sq->vPtr[DRAW_INDEX].voidMethod(sq);
}

