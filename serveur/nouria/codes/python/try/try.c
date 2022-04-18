#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct ctx_s {
    char * sp;
    char * bp;
    int magic;
};


typedef int (func_t)(struct ctx_s *, int); 

//fonction try
int try(struct ctx_s *pctx, func_t *f, int a)
{
    asm("mov %%esp, %0" : "= r"(pctx->sp));
    asm("mov %%ebp, %0" : "= r"(pctx->bp));    
    
    printf("Try   : %p - %p\n",pctx->sp,pctx->bp);

    return f(pctx, a);
}

//fonction throw
int throw(struct ctx_s *pctx, int r)
{
    assert(pctx->magic == 212);
    static int res;
    res = r;
    static struct ctx_s * p2 ;
    p2 = (struct ctx_s *) malloc(sizeof(struct ctx_s));
    p2->sp = pctx->sp;
    p2->bp = pctx->bp;
    p2->magic = pctx->magic;

    printf("Throw : %p - %p\n",pctx->sp,pctx->bp);
    
    asm("mov %0, %%esp"
        "\n\t"
        "mov %1, %%ebp"
        :
        : "r"(pctx->sp), "r"(pctx->bp));

    return res;
}

static int mul(struct ctx_s *pctx, int depth)
{
    int i;

    struct ctx_s * p = (struct ctx_s *) malloc(sizeof(struct ctx_s));
    asm("mov %%esp, %0" : "= r"(p->sp));
    asm("mov %%ebp, %0" : "= r"(p->bp));
    p->magic = 212;

    printf("Mul   : %p - %p\n",p->sp,p->bp);

    switch (scanf("%d", &i)) {
        case EOF :
            return 1; /* neutral element */
        case 0 :
            return mul(pctx, depth+1); /* erroneous read */
        case 1 :
            if (i) 
                return i * mul(pctx, depth+1);
            else
                throw(pctx,0);
    }
    return 1;
}


int main(void)
{ 
    struct ctx_s * p = (struct ctx_s *) malloc(sizeof(struct ctx_s));
    asm("mov %%esp, %0" : "= r"(p->sp));
    asm("mov %%ebp, %0" : "= r"(p->bp));
    p->magic = 212;

    printf("Main  : %p - %p\n",p->sp,p->bp);

    int val = try(p,&mul,1);

    printf("%d\n",val);

    return 0;
}
