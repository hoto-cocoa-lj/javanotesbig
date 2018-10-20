x=0
n=5
a=list(range(1,1+n))
a.reverse()
b=[]
t=[]

def f(a,b,t,n):
    global x  
    if n==1:
       g(a,b,t)
       x+=1
       
    else:
        f(a,t,b,n-1)
        f(a,b,t,1)
        
        f(t,b,a,n-1)

def g(a1,b1,t1):
    print(a,'\t'*2,b,'\t'*2,t)
    b1.append(a1.pop())
    
def printf(a1,b1,t1):
    a1=a1.copy()
    a1.reverse()
    b1=b1.copy()
    b1.reverse()
    t1=t1.copy()
    t1.reverse()
    print("a"*22)
    for i in a1:
        print('*'*i)
    print("b"*22)
    for i in b1:
        print('*'*i)
    print("t"*22)
    for i in t1:
        print('*'*i)
    
    print("="*77)
    
    
f(a,t,b,n)

print(x)
