{ pkgs ? import <nixpkgs> { } }:

with pkgs;

mkShell {
  buildInputs = [
    openjdk
    clang-tools # clang-format
    groovy # Java REPL
    (python3.withPackages (p: with p; [ black pyflakes isort matplotlib ]))
    jdt-language-server
  ];
}
