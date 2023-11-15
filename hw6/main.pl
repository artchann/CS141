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

my_remove(_, [], []).
my_remove(H, [H|T], R) :- my_remove(H, T, R).
my_remove(X, [H|T], [H|NewTail]) :- my_remove(X, T, NewTail).


%Error present, won't return list but performs all substitutions
my_subst(_F, _R, [], []).
my_subst(F, R, [H|T], [H|NewTail]) :- my_subst(F, R, T, NewTail).
my_subst(F, R, [F|T], [R|NewTail]) :- my_subst(F, R, T, NewTail).

my_subset(_P, [], []).
my_subset(P, [H|T], [H|NewTail]) :- call(P, H), my_subset(P, T, NewTail).
my_subset(P, [_H|T], NewTail) :- my_subset(P, T, NewTail).

mult_by_two([], []).
mult_by_two([H|T], [NewH|R]) :-
    NewH is H * 2, mult_by_two(T, R).
