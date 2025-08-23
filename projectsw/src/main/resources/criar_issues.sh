#!/bin/bash

# Repositório alvo no GitHub
REPO="melrojohnn/projectsw"

# Arquivo JSON com as issues
ISSUES_FILE="issues.json"

# Verifica se o arquivo existe
if [ ! -f "$ISSUES_FILE" ]; then
  echo "Arquivo $ISSUES_FILE não encontrado!"
  exit 1
fi

# Ler cada item do JSON e criar a issue
jq -c '.[]' $ISSUES_FILE | while read i; do
  TITLE=$(echo $i | jq -r '.title')
  BODY=$(echo $i | jq -r '.body')

  echo "Criando issue: $TITLE"
  gh issue create --repo $REPO --title "$TITLE" --body "$BODY"
done

echo "Todas as issues foram criadas!"
