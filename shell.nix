{ pkgs ? import <nixpkgs> { } }:

with pkgs;

mkShell {
  buildInputs = [
    openjdk
    clang-tools # clang-format
    (python3.withPackages (p: with p; [ python-lsp-server ]))
    jdt-language-server
    ];
}
