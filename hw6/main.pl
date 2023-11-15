my_length([],0).
my_length([H|T],R) :- my_length(T, TLen), R is TLen + 1.

my_member(_A,[]) :- false.
my_member(A, [H|_T]) :- A == H.
my_member(A, [_H|T]) :- mem(A,T).

my_append([], L, L).
my_append([H|T], L, [H|NewT]) :- my_append(T,L,NewT).

my_reverse([], []).
my_reverse([H|T], R) :- my_reverse(T, NextR), myappend(NextR, [H], R). %[H] constructs a new list.. how come this is necessary?

my_nth([], _R, []).
my_nth([H|T], 0, [H|T]).
% my_nth([H|T], R, [H2|T2]) :- R < 0 //condition?
my_nth([_H|T], R, L) :- New is R -1, my_nth(T, NewR, L).



mult_by_two([], []).
mult_by_two([H|T], [NewH|R]) :-
    NewH is H * 2, mult_by_two(T, R).
