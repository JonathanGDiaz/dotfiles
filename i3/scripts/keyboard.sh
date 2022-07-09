#!/usr/bin/bash
if setxkbmap -query | grep -o us ; then 
    setxkbmap latam , ;
else 
    setxkbmap us , ;
fi
