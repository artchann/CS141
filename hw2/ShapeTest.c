#include <malloc.h>
#include <iostream>
#include <cstring>

using namespace std;

typedef double (*double_method_type)(void *);
typedef void (*void_method_type)(void *);
typedef char* (*char_method_type)(void *);
typedef void (*picture_method_type)(void *, int);

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
    printf("%.2f\n",_this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
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
    printf("%.2f\n", _this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
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
    printf("%.2f\n", _this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
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

struct Rectangle{
    VTableUnit* vPtr;
    char* name;
    int length;
    int width;
};

//Rectangle functions

double getRectArea(Rectangle* _this){
    return (double)_this->length*(double)_this->width;
}

void printRectangle(Rectangle* _this){
    printf("%s", _this->vPtr[GET_NAME_INDEX].charMethod((Shape*)_this));
    printf("(");
    printf("%i", _this->length);
    printf(", ");
    printf("%i", _this->width);
    printf(") : ");
    printf("%.2f\n", _this->vPtr[GET_AREA_INDEX].doubleMethod(_this));
}

void drawRectangle(Rectangle* _this){
    printf("***\n");
    printf("* *\n");
    printf("* *\n");
    printf("* *\n");
    printf("***\n");

}

VTableUnit Rectangle_VTable [] = {
    {.charMethod = (char_method_type)getName},
    {.doubleMethod = (double_method_type)getRectArea},
    {.voidMethod = (void_method_type)printRectangle},
    {.voidMethod = (void_method_type)drawRectangle}
};

Rectangle* Rectangle_Constructor(Rectangle* _this, char* name, int length, int width){
    Square_Constructor((Square*)_this, name, length);
    _this->vPtr = Rectangle_VTable;
    _this->width = width;
    return _this;
}
//

void drawAll(Shape* allShapes[], int numShapes){
    for (int i = 0 ;i < numShapes; i++){
        allShapes[i]->vPtr[DRAW_INDEX].voidMethod(allShapes[i]);
    }
}

void printAll(Shape* allShapes[], int numShapes){
    for(int i=0; i < numShapes; i++){
        allShapes[i]->vPtr[PRINT_INDEX].voidMethod(allShapes[i]);
    }
}

double totalArea(Shape* allShapes[], int numShapes){
    double sum =0;
    for(int i=0; i< numShapes; i++){
        sum += allShapes[i]->vPtr[GET_AREA_INDEX].doubleMethod(allShapes[i]);
    }
    return sum;
}

int main(int argc, char* argv[]){
    //printf("%s\n", argv[1]);
    
    //int x = atoi(argv[1]);
    //printf("%i\n", x);

    char triName [] = "FirstTriangle";
    char triNameTwo [] = "SecondTriangle";
    char circName [] = "FirstCircle";
    char circNameTwo [] = "SecondCircle";
    char squareName[] = "FirstSquare";
    char squareNameTwo[] = "SecondSquare";
    char rectangleName[] = "FirstRectangle";
    char rectangleNameTwo[] = "SecondRectangle";


    int arg1 = atoi(argv[1]); 
    int arg2 = atoi(argv[2]);

    Shape* picture[] = {
        (Shape*)Triangle_Constructor((Triangle*)malloc(sizeof(Triangle)), triName, arg1, arg2),
        (Shape*)Triangle_Constructor((Triangle*)malloc(sizeof(Triangle)), triNameTwo, arg1-1, arg2-1),
        (Shape*)Circle_Constructor((Circle*)malloc(sizeof(Circle)), circName, arg1),
        (Shape*)Circle_Constructor((Circle*)malloc(sizeof(Circle)), circNameTwo, arg1-1),
        (Shape*)Square_Constructor((Square*)malloc(sizeof(Square)), squareName, arg1),
        (Shape*)Square_Constructor((Square*)malloc(sizeof(Square)), squareNameTwo, arg1-1),
        (Shape*)Rectangle_Constructor((Rectangle*)malloc(sizeof(Rectangle)), rectangleName, arg1,arg2),
        (Shape*)Rectangle_Constructor((Rectangle*)malloc(sizeof(Rectangle)), rectangleNameTwo, arg1-1, arg2-1)
    };
    printAll(picture,8);
    drawAll(picture, 8);
    printf("%.2f\n", totalArea(picture,8));
}

