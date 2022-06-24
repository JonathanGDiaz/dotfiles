:set number
:set relativenumber
:set autoindent
:set tabstop=4
:set shiftwidth
:set smarttab
:set softtabstop=4
:set mouse=a
:set wrap
:syntax on

"Iniciamos a llamar los plugins
call plug#begin()

Plug 'https://github.com/vim-airline/vim-airline'
Plug 'https://github.com/rafi/awesome-vim-colorschemes'
Plug 'https://github.com/sheerun/vim-polyglot'
Plug 'https://github.com/jiangmiao/auto-pairs'
"Plug 'https://github.com/neoclide/coc.nvim', {'branch': 'release'}

call plug#end()
"Posibles colores
":colorscheme nord
"Seleccionamos el color de tema
":colorscheme deus
"Comando para guardar el documento
nnoremap <C-s> :w<CR>
"Comando para guardar y salir del documento
nnoremap <C-w> :wq<CR>
"Comando para salir sin guardar del documento
nnoremap <C-q> :q!<CR>
