//============================================================================
// Name        : Cpl.cpp
// Author      : Raviragul Balakrishnan
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

class TowerOfHonoi
{
public:
	int tower(string in,string out,int n){
			if(in.substr(0,1)==out.substr(0,1)){
				tower(in.substr(1,in.size()),out.substr(1,out.size()),n-1);
			}else{
				move(in,out);
			}
		return 0;
	}
	int move(string in,string out){
		if(in.size()==1){
			if(in==out){
				return 0;
			}
			cout<<"Move "<<in<<" to "<<out<<endl;
			return 0;
		}
		string s1 = in.substr(0,1);
		string s2 = out.substr(0,1);
		if(s1==s2){
			move(in.substr(1,in.size()),out.substr(1,in.size()));
			return 0;
		}
		string s = getAlternate(s1,s2);
		string ss  = "";
		for(unsigned int i=1;i<in.size();i++){
			ss = ss+s;
		}
		move(in.substr(1,in.size()),ss);
		cout<<"Move "<<s1<<" to "<<s2<<endl;
		move(ss,out.substr(1,out.size()));
		return 0;
	}
	string getAlternate(string s1,string s2){
		if((s1 == "A" and s2 == "B") or (s1 == "B" and s2 == "A")){
			return "C";
		}else if((s1 == "C" and s2 == "B") or (s1 == "B" and s2 == "C")){
			return "A";
		}else{
			return "B";
		}
	}
};
int main() {
	TowerOfHonoi obj;
	string input,output;
	cout << "Please enter initial configuration  : ";
	cin>>input;
	cout << "Please enter output configuration  : ";
	cin>>output;
	int n = input.size();
	obj.tower(input,output,n);
	return 0;
}

