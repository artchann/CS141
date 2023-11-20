my_length([],0).
my_length([_|T],R) :- my_length(T, TLen), R is TLen + 1.

my_member(_A,[]) :- false.
my_member(A, [A|_T]).
my_member(A, [H|T]) :- A\==H,  mem(A,T).

my_append([], L, L).
my_append([H|T], L, [H|NewT]) :- my_append(T,L,NewT).

my_reverse_helper([], R ,R).
my_reverse_helper([H|T], R, Result) :- my_reverse_helper(T, R, [H|Result]).
my_reverse(L, R) :- my_reverse_helper(L, R, []).

my_nth([], _R, []).
my_nth([H|T], 1, [H|T]).
% my_nth([H|T], R, [H2|T2]) :- R < 0 //condition?
my_nth([_H|T], R, L) :- NewR is R -1, my_nth(T, NewR, L).

my_remove(_, [], []).
my_remove(H, [H|T], R) :- my_remove(H, T, R).
my_remove(X, [H|T], [H|NewTail]) :- my_remove(X, T, NewTail).


%Error present, won't return list but performs all substitutions
my_subst(_F, _R, [], []).
my_subst(F, R, [H|T], [H|NewTail]) :- F\==H, my_subst(F, R, T, NewTail).
my_subst(F, R, [F|T], [R|NewTail]) :- my_subst(F, R, T, NewTail).

my_subset(_P, [], []).
my_subset(P, [H|T], [H|NewTail]) :- call(P, H), my_subset(P, T, NewTail).
my_subset(P, [_H|T], NewTail) :- my_subset(P, T, NewTail).

my_merge([], L, L).
my_merge(L, [], L).
my_merge([H1|T1], [H2|T2], [H1|R]) :- H1 =< H2, my_merge(T1, [H2|T2], R).
my_merge([H1|T1], [H2|T2], [H2|R]) :- H2 < H1, my_merge([H1|T1], T2, R).

sublist_check([], _, _O).
sublist_check(_L, [], _O):- false. %might have to check if _L is a list still (?)
%run sublist check and go through list if element is found
%if found, keep checking,
sublist_check([H|T], [H|TFind], O) :- sublist_check(T, TFind, O).
%if not found, send the HEAD of the list back to my_sublist
%O is original list
sublist_check([H|_T], [HFind|TFind], O) :- H \== HFind, 
              my_sublist(O, TFind).

my_sublist([], _L).
my_sublist(_L, []) :- false.
my_sublist([], []).
%first matches
my_sublist([H|T], [H|TFind]) :- sublist_check(T, TFind, H).
%keep going
%PAY ATTENTION TO HOW LISTS ARE PASSED IN, THEY CAN BRICK YOUR PROGRAM
%Cannot unify with head to the REST of list, it only gets a single item
my_sublist([H|T], [HFind|TFind]) :- H \== HFind, my_sublist([H|T], TFind).


my_assoc(_, [], _R) :- false.
my_assoc(X, [X, A2|_T], A2).
my_assoc(X, [A, _A2|T], R) :- X\==A, my_assoc(X, T, R).

my_add([0], [0], R) :- R =[0].
my_add([], L2, R) :- my_append(R, L2, R).
my_add(L1, [], R) :- my_append(R, L1, R).

my_replace(_, [], []).
my_replace(Alist, [H|T], [R|NewTail]) :- my_assoc(H, Alist, R),
    my_replace(Alist, T, NewTail).
my_replace(Alist,[H|T], [H|NewTail]) :- my_replace(Alist, T, NewTail). 

mult_by_two([], []).
mult_by_two([H|T], [NewH|R]) :-
    NewH is H * 2, mult_by_two(T, R).
