@Echo Off
:: 1. ����������� �� ��४��� ���
cd ..\ 
:: 1. ������� ����� Practice0
md Practice0 
:: ���室�� � ����� Practice0
cd Practice0 
:: 2. ������� ����� Test
md Test
:: ���室�� � ����� Test
cd Test
:: 2. ������� � �����뢠�� � 䠩� about.me ��� � 䠬����
Echo ����ᠭ�� �㤠७��> about.me
:: 2. �����뢠�� 㭨������ ᫥���饩 ��ப��
Echo �㬃�>> about.me
:: 3. ��६�頥� 䠩� about.me �� ��४��� ���
move about.me ..\ 
:: ����������� �� ��४��� ���
cd ..\ 
:: 3. ���⠢�塞 䠩�� about.me ��ਡ�� read only
attrib +r about.me
:: ����砥� ���७�� ��६����� �।�
SetLocal EnableDelayedExpansion
:: ��ᢠ����� ��६����� � ���祭�� 0, /� 㪠�뢠��, �� ��ࠬ��� ��ப� ���� ����塞� �᫮�� ��ࠦ�����
set /a c=0
:: ���� ����� ��ॡ�ࠥ� ��ப� � 䠩��, ��ப� ��।����� � ��६����� �
for /f "UseBackQ Delims=" %%A IN ("about.me") do (
  :: ���६����㥬 ��६����� �
  set /a c+=1
  :: �᫨ � ࠢ�� 1, ��ᢠ����� ��६����� � ���祭�� �
  if !c!==1 set "a=%%A"
  :: �᫨ � ࠢ�� 1, ��ᢠ����� ��६����� � ���祭�� �
  if !c!==2 set "b=%%A"
)
:: 4. �뢮��� ᮮ�饭��
echo %a% �稫�� � %b%
:: 5. �����㥬 䠩� about.me � �����४��� Test
copy /y about.me Test\
:: 5. ��२�����뢠�� 䠩� Test\about.me � about_.me
ren Test\about.me about_.me
:: 6. �����뢠�� � ����� 䠩�� ��� ����砭�� 㭨������
Echo 2012>> about.me
:: 7. ���ࠥ� ��ਡ�� read only
attrib -r about.me
:: 7. �����㥬 䠩� about.me � �����४��� Test
copy /y about.me Test\
:: ����塞 䠩� about_.me, ��� ��������� ���䫨�⮢ �� ��२���������
del /q Test\about_.me
:: 7. ��२�����뢠�� 䠩� Test\about.me � about_.me
ren Test\about.me about_.me
:: 7. �����뢠�� � ����� 䠩�� ��� ����砭�� 㭨������
Echo 2012>> about.me
:: 8. �ࠢ������ ᮤ�ন��� 䠩���, �⫨�� �뢮����� �� �࠭
fc about.me Test\about_.me
pause