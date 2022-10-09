function union = concatena(u)

num=1:256;
va=0;
    for v=1:256
        h(v)=va+u(v);
        va=h(v);
    end
val=288-(h*200);
union(:,1)=num;
union(:,2)=val;
end