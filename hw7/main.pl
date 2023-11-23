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
simplify(X * Num, X) :- char_type(X, alpha), Result is Num, Result == 1. 

%case 2 uses first rule
%first term is 1*x, second term is - 0
simplify(X - Num, R) :- Result is Num, Result == 0, simplify(X, R).
simplify(X * Num, Num) :- char_type(Num, alpha), number(X), X == 1.

%case 3
simplify(X + Num, Result + Num) :- char_type(Num, alpha), Result is X.
%simplify(X * Num, Result) :- Result is X, number(Result), number(Num), Result is Result * Num.

%simplify(X, _) :- number(X).
simplify(X, _) :- integer(X).
simplify(X + Num, R) :- number(Num), R is X + Num.
simplify(X * Num, R) :- number(Num), R is X * Num.
simplify(X - Num, R) :- number(Num), R is X - Num.
simplify(X / Num, R) :- number(Num), R is X / Num.
simplify(X * 1, X).
simplify(X + 0, X).
simplify(_X * 0, 0).

deriv(C, 0) :- number(C).
deriv(X, 1) :- atom(X).

deriv(X^N, N*X^(N1)) :- number(N), N1 is N-1.
deriv(A+B, D1+D2) :- deriv(A, D1), deriv(B, D2).

party_seating(L) :- S is 10, seating_check(L, S).
%can seat anyone
seating_check([X|T] , S) :- S == 10, number_seats(S, Result), male(X), seating_check(T, Result).
seating_check([X,T] , S) :- S == 10, number_seats(S, Result), female(X), seating_check(T, Result).

seating_check(H|T, S) :- number_seats(S, Result), male(H), speaks(H, language(L)), female(T), speaks(T, language(L)), seating_check(T, Result).
seating_check(H|T, S) :- number_seats(S, Result), female(H), speaks(H, language(L)), male(T), speaks(T, language(L)), seating_check(T, Result).

%seating_check([H|T], S) :- number_seats(S,Result), 


%number_seats(Num) :- Num == 10, Num is Num - 1.
number_seats(Num, Result) :- Num > 0, Result is Num - 1.
%number_seats(Num, Result) :- Num2 == 0, false.
