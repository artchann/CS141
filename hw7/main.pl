eval(X + Num, R) :- number(Num), R is X + Num.
eval(X * Num, R) :- number(Num), R is X * Num.
eval(X - Num, R) :- number(Num), R is X - Num.
eval(X / Num, R) :- number(Num), R is X / Num.

%IF LAST TERM IS A DIGIT AND THE FOLLOWING TERM IS A COMPOUND
%simplify(X + Num, R) :- number(Num), compound(X), simplify(X,R), R = X + R.
%LAST TERM IS A COMPOUND SOLVE IT // HOW DO I APPEND MY VALUES ONTO R SO THAT IT RETURNS AS AN EQUATION
%simplify(X - Num, R) :- number(X), compound(Num), simplify(Num, R), R = .
%Single term with coefficient
%simplify(X * Num, R) :- Result is Num, integer(Result), Result == 1,R = X.


%IF LAST TERM IS A DIGIT AND THE FOLLOWING TERM IS A COMPOUND
simplify(X + Num, R + Num) :- number(Num), compound(X), simplify(X,R).
%IF FIRST TERM IS A NUMBER MINUS A COMPOUND -- SIMPLIFY COMPOUND
simplify(X - Num, X - R) :- number(X), compound(Num), simplify(Num, R).
%COMPOUND HAS A COEFFICIENT OF ONE, RETURN JUST THE VARIABLE
simplify(X * Num, X) :- Result is Num, Result == 1. 


%case 2 uses first rule
%first term is 1*x, second term is - 0
simplify(X - Num, R) :- Result is Num, Result == 0, simplify(X, R).
simplify(X * Num, Num) :- char_type(Num, alpha), number(X), X == 1.


%simplify(X, _) :- number(X).
simplify(X, _) :- integer(X).
simplify(X + Num, R) :- number(Num), R is X + Num.
simplify(X * Num, R) :- number(Num), R is X * Num.
simplify(X - Num, R) :- number(Num), R is X - Num.
simplify(X / Num, R) :- number(Num), R is X / Num.
simplify(X * 1, X).
simplify(X + 0, X).
simplify(_X * 0, 0).
