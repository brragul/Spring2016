__author__ = "Raviragul Balakrishnan"
class MyClass:
    def move(i,o):
        if len(i)==1:
            if i == o:
                return
            print("Move from", i + " to " + o)
            return
        s1 = i[0]
        s2 = o[0]
        if s1 == s2:
            MyClass.move(i[1:len(i)],o[1:len(o)])
            return
        s = MyClass.getAlternatePole(s1,s2)
        ss = ""
        for x in range(1,len(i)):
            ss = ss+s
        MyClass.move(i[1:len(i)],ss)
        print("Move from",s1+" to "+s2)
        MyClass.move(ss,o[1:len(o)])
        return

    def getAlternatePole(s1,s2):
        if (s1 == "A" and s2 == "B") or (s1 == "B" and s2 == "A"):
            return 'C'
        elif (s1 == "C" and s2 == "B") or (s1 == "B" and s2 == "C"):
            return 'A'
        else:
            return 'B'

    @staticmethod
    def tower(i, o, n):
        if i[0] == o[0]:
            MyClass.tower(i[1:len(i)], o[1:len(o)], n - 1)
        else:
            MyClass.move(i, o)
        return
start = input("Please enter initial configuration  : ")
end = input("Please enter output configuration  : ")
myObj = MyClass()
myObj.tower(start,end,len(start))
del myObj