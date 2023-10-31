(defun MY-LENGTH (x)
    (cond ((null x) 0)
        (T (+ 1 (my-length (cdr x)))))
)

(defun MY-MEMQ (A L) 
    (cond ((null L) nil)
    ( (eq (car L) A) (cons (car L) (cdr L)))
    ( T (MY-MEMQ A (cdr L))))
)

