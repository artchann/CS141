eval(X + Num, R) :- number(Num), R is X + Num.
eval(X * Num, R) :- number(Num), R is X * Num.
eval(X - Num, R) :- number(Num), R is X - Num.
eval(X / Num, R) :- number(Num), R is X / Num.
