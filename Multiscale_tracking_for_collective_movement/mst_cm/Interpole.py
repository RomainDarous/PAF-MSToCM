import matplotlib.pyplot as plt
import csv
import numpy as np
from mpl_toolkits import mplot3d
import sympy
from sympy import poly
import math as m
from sympy.abc import x
from sympy.abc import t
import os
import argparse

file = open(os.path.join(os.path.dirname(__file__), "data.csv"))
print(file)
fichier = csv.reader(file, delimiter=',')

def init_cmd():
    ap = argparse.ArgumentParser()
    ap.add_argument("-g", "--groupid", default=1, help="LOL")
    args = vars(ap.parse_args())
    return args


args = init_cmd()


idgroupe=int(args["groupid"])

def trajectoireBarycentre(idgroupe):



    X=[]
    Y=[]
    Z=[]
    separationX=[]
    separationY=[]
    separationZ=[]
    fusionX=[]
    fusionY=[]
    fusionZ=[]
    iter=0
    res=[]



    for ligne in fichier:
        res.append(ligne)
    
    for i in range(len(res)):
        ligne = res[i]

        if iter ==2:


            if (ligne[0] == str(idgroupe))and(len(ligne)==len(res[1])):
                X.append(float(ligne[1]))
                Y.append(float(ligne[2]))
                Z.append(int(ligne[-1]))
                #print(float(ligne[1]))

                if int(ligne[7]) > nb:
                    separationX.append(float(ligne[1]))
                    separationY.append(float(ligne[2]))
                    separationZ.append(int(ligne[-1]))
                    nb = int(ligne[7])
                elif int(ligne[7]) < nb:
                    fusionX.append(float(ligne[1]))
                    fusionY.append(float(ligne[2]))
                    fusionZ.append(int(ligne[-1]))
                    nb = int(ligne[7])


        elif iter==1:
            nb=int(ligne[7])
            iter=2
            #groupe.append(ligne)
            X.append(float(ligne[1]))
            Y.append(float(ligne[2]))
            Z.append(int(ligne[-1]))
            nb = int(ligne[7])

        else:
            iter=1

    #print(Y)
    return (X,Y,Z,separationX,separationY,separationZ,fusionX,fusionY,fusionZ)

def Lagrange (Lx, Ly):
    x=sympy.symbols('x')
    if  len(Lx)!= len(Ly):
        return 1
    y=0
    for k in range ( len(Lx) ):
        t=1
        for j in range ( len(Lx) ):
            if j != k:
                t=t* ( (x-Lx[j]) /(Lx[k]-Lx[j]) )
        y+= t*Ly[k]
    return (poly(y))#y.subs(x,time)

def erreur(listeX1,listeY1,listeX2,listeY2):
    res=[]

    for i in range(len(listeX1)):
        d=m.sqrt((listeX1[i]-listeX2[i])**2+(listeY1[i]-listeY2[i])**2)
        res.append(d)
    return res

def interpole(X,Y,Z,k):

    interpoleX=[]
    interpoleY=[]
    formuleX=[]
    formuleY=[]
    #print(X)
    #print(Y)
    #print(Z)


    Xi= selection(X,k)
    Yi= selection(Y,k)
    Zi = selection(Z,k)
    #print(Xi)
    #print(Yi)
    #print(Zi)

    LX=Lagrange(Zi,Xi)
    LY=Lagrange(Zi,Yi)

    for i in range(len(X)):
        interpoleX.append(LX.eval(Z[i]))
        interpoleY.append(LY.eval(Z[i]))

    formuleX.append(Lagrange(Zi,Xi))
    formuleY.append(Lagrange(Zi,Yi))


    return(interpoleX,interpoleY,formuleX,formuleY)


def selection(liste,k):
    res=[]
    res.append(liste[0])
    selec=len(liste)//(k-2)
    i=0
    for elt in liste:
        if i==selec:
            res.append(elt)
            i=0
        else:
            i+=1
    res.append(liste[-1])
    return res


def segmentation(liste,changementZ):
    res=[]
    i=0
    acc=[]

    for elt in liste:
        if i in changementZ:

            acc.append(elt)
            res.append(acc)
            acc=[]
            i+=1
        else:
            acc.append(elt)
            i+=1
    res.append(acc)
    return res


def approximation(X,Y,Z,pas,nb_max,deg_max):
    changementZ=changements_dir(X,Y,Z,pas,nb_max)[2]
    changementX=changements_dir(X,Y,Z,pas,nb_max)[0]
    changementY=changements_dir(X,Y,Z,pas,nb_max)[1]




    Xa=segmentation(X,changementZ)
    Ya=segmentation(Y,changementZ)
    Za=segmentation(Z,changementZ)

    Xt=[]
    Yt=[]
    Zt=[]
    fX=[]
    fY=[]


    for i in range(len(Xa)):


        resX,resY,formuleX,formuleY=interpole(Xa[i],Ya[i],Za[i],deg_max)
        fX.append(formuleX)
        fY.append(formuleY)


        for j in range(len(resX)):
            Xt.append(resX[j])
            Yt.append(resY[j])
            Zt.append(Za[i][j])

    changementX=[X[0]]+changementX
    changementX.append(X[-1])

    changementY=[Y[0]]+changementY
    changementY.append(Y[-1])

    changementZ=[Z[0]]+changementZ
    changementZ.append(Z[-1])


    fig = plt.figure(figsize=plt.figaspect(2.))
    fig.suptitle('Comparaison des trajectoires réelles et approximées')

    ax = fig.add_subplot(1, 2, 1, projection='3d')
    ax.plot3D(X, Y, Z, 'green')
    ax.set_xlabel('x')
    ax.set_ylabel('y')
    ax.set_zlabel('temps')
    ax.scatter(separationX, separationY, separationZ,label ='Separation', c='r')
    ax.scatter(fusionX, fusionY, fusionZ,label ='fusion', c='b')
    ax.legend(loc='upper left')
    ax.set_title('Trajectoire du groupe '+ str(idgroupe))

    ax = fig.add_subplot(1, 2, 2, projection='3d')
    ax.plot3D(Xt,Yt, Zt, 'red')
    ax.scatter(changementX, changementY, changementZ,label ='Branche', c='purple')
    ax.set_xlabel('x')
    ax.set_ylabel('y')
    ax.set_zlabel('temps')
    ax.set_title('Trajectoire du groupe '+ str(idgroupe)+' interpolée')
    ax.legend(loc='upper right')

    #manager = plt.get_current_fig_manager()
    #manager.window.showMaximized()

    plt.figure()

    plt.axis('off')
    y=1

    i=0
    borne1=changementZ[i]
    borne2=changementZ[i+1]

    plt.text(0,y,"Formules pour x(t)",fontsize = 13)
    y-=0.05

    for f in fX:
        s=polytostring(f[0])
        s="x(t)="+s+" pour t dans ["+str(borne1)+";"+str(borne2)+"]"
        plt.text(0,y,s,fontsize = 7)
        y-=0.05
        #print(borne1)
        #print(borne2)
        i+=1
        if (i+1)<len(changementZ):
            borne1=changementZ[i]
            borne2=changementZ[i+1]

    i=0
    borne1=changementZ[i]
    borne2=changementZ[i+1]
    #print(changementZ)
    plt.text(0,y,"",fontsize = 13)
    y-=0.05
    plt.text(0,y,"Formules pour y(t)",fontsize = 13)
    y-=0.05

    for f in fY:
        s=polytostring(f[0])
        s="y(t)="+s+" pour t dans ["+str(borne1)+";"+str(borne2)+"]"
        plt.text(0,y,s,fontsize = 7)
        y-=0.05

        i+=1
        if (i+1)<len(changementZ):
            borne1=changementZ[i]
            borne2=changementZ[i+1]

    #manager = plt.get_current_fig_manager()
    #manager.window.showMaximized()

    plt.figure()
    plt.title("Erreur entre les trajectoires réelles et interpolées en fonction du temps ")
    plt.xlabel("Temps")
    plt.ylabel("Erreur(pixels)")
    erreurInterpole= erreur(X,Y,Xt,Yt)

    plt.plot(Z,erreurInterpole)

    plt.show()


def derive(liste):
    res=[]
    for i in range(1,len(liste)):
        de=liste[i]-liste[i-1]
        res.append(de)

    return(res)


def changements_dir(X,Y,Z,pas,nb_max):
    seuil=1
    changementX=(nb_max+1)*[0]
    ecart_min=len(Z)/(1.5*nb_max)

    while(len(changementX)>nb_max):
        dx=derive(X)
        dy=derive(Y)


        changementX=[]
        changementY=[]
        changementZ=[]

        refusX=[]
        refusY=[]
        refusZ=[]

        for i in range(pas,len(dx)-pas):
            if compare(dx[i-pas],dy[i-pas],dx[i],dy[i],dx[i+pas],dy[i+pas],seuil):
                if (len(changementZ)!=0)and (i-changementZ[-1])>nb_max:

                    changementX.append(X[i+pas])
                    changementY.append(Y[i+pas])
                    changementZ.append(Z[i+pas])
                elif len(changementZ)==0:
                    changementX.append(X[i+pas])
                    changementY.append(Y[i+pas])
                    changementZ.append(Z[i+pas])

                else:
                    refusX.append(X[i+pas])
                    refusY.append(Y[i+pas])
                    refusZ.append(Z[i+pas])
        #print(seuil,len(changementX))

        seuil=seuil/2


    return(changementX,changementY,changementZ,refusX,refusY,refusZ)

def sgn(x):
    if x>0:
        return(1)
    elif x<0:
        return(-1)
    else:
        return(0)


def compare(xa,ya,xb,yb,xc,yc,seuil):
    ps=(yb-ya)*(yc-ya)+(xb-xa)*(xc-xa)
    nab=m.sqrt((xb-xa)**2+(yb-ya)**2)
    nac=m.sqrt((xc-xa)**2+(yc-ya)**2)
    return(abs(ps/(nab*nac))<seuil)


def polytostring(formule):
    liste=formule.all_coeffs()
    l=[]
    for num in liste:
       l.append(round(num,2))

    f=sympy.Poly(l,t)
    s=str(f)

    return(s[5:len(s)-17])





tr=trajectoireBarycentre(idgroupe)
X=tr[0]
Y=tr[1]
Z=tr[2]
separationX=tr[3]
separationY=tr[4]
separationZ=tr[5]
fusionX=tr[6]
fusionY=tr[7]
fusionZ=tr[8]

#
#
#
# #changementZ=changements_dir(X,Y,Z,15,10)[2]
#
#
# #Xa=segmentation(X,changementZ)
# #Ya=segmentation(Y,changementZ)
# #Xs=selection(Xa[0],5)
# #print(Xs)
#
#
print(Z)
if len(Z)>1:
    approximation(X,Y,Z,15,10,4)
    print("MDR")

